package com.vc.wd.common.util.logger.printer;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.vc.wd.common.util.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class FileLogger {
    private Logger logger = Logger.createLogger(getClass());
    private static final int ENABLE_LEVEL = Log.VERBOSE;
    private static final int LOG_COUNT = 100;

    private String m_strFileName = "";
    private final Queue<String> m_quMsgs = new LinkedList<String>();

    // 因为日志只在一个进程中，所以只用一个锁就可以
    private final Object m_obLock = new Object();


    public void init(String path, Context ctx) {

        logger.i("environment dir = %s", Environment.getExternalStorageDirectory());
        logger.i("path = %s", path);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        String timeStamp = dateFormat.format(new Date());
        this.m_strFileName = path + "/" + timeStamp + getCurrentProcessName(ctx) + ".vvlog";
        logger.i("m_strFileName = %s", m_strFileName);
        File dir = new File(path);
        logger.i("dir is exists = %s ,%b", String.valueOf(dir), dir.exists());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private String getCurrentProcessName(Context ctx) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : activityManager.getRunningAppProcesses()) {
            if (pid == appProcessInfo.pid) {
                return appProcessInfo.processName;
            }
        }
        return "";
    }

    public void write(int level, String tag, String msg) {
        if (level < ENABLE_LEVEL) {
            return;
        }
        if (msg != null && msg.length() > 300) {
            msg = msg.substring(0, 300);
        }
        String threadId = String.valueOf(Thread.currentThread().getId());
        SimpleDateFormat timeFmt = new SimpleDateFormat("hh:mm:ss:SSS");
        String formatMsg = String.format("%s %s %s (%s) %s\r\n", this.formatLevel(level), String.valueOf(timeFmt.format(new Date())), this.formatTag(tag), threadId, msg);

        //logger.i("formattMsg = %s",formatMsg);
        synchronized (this.m_quMsgs) {
            this.m_quMsgs.add(formatMsg);
        }

        if (this.m_quMsgs.size() >= LOG_COUNT) {
            this.flush();
        }
    }

    public String getFileName() {
        return m_strFileName;
    }

    private String formatLevel(int level) {
        switch (level) {
            case Log.VERBOSE:
                return "V";
            case Log.DEBUG:
                return "D";
            case Log.INFO:
                return "I";
            case Log.WARN:
                return "W";
            case Log.ERROR:
                return "E";
            default:
                return "U";
        }
    }

    public void flush() {
        Queue<String> queue = new LinkedList<String>();

        synchronized (this.m_quMsgs) {
            queue.addAll(this.m_quMsgs);
            this.m_quMsgs.clear();
        }
        if (queue.isEmpty()) {
            return;
        }

        synchronized (m_obLock) {
            FileLock lock = null;
            FileWriter writer = null;
            try {
                writer = new FileWriter(this.m_strFileName, true);
                logger.i("flush log m_strFileName = %s", m_strFileName);
                for (String s : queue) {
                    writer.write(s);
                }
                writer.flush();
            } catch (FileNotFoundException e) {
                logger.e(e, "flush() FileNotFoundException");
                return;
            } catch (IOException e) {
                logger.e(e, "flush() IOException");
                return;
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    logger.e(e, "flush() finally1 IOException");
                    e.printStackTrace();
                }
                try {
                    if (lock != null) {
                        lock.release();
                    }
                } catch (IOException e) {
                    logger.e(e, "flush() finally2 IOException");
                    e.printStackTrace();
                }
            }
        }
    }

    private String formatTag(String tag) {
        String result = tag;
        int end = tag.length();
        if (end > 50) {
            result = tag.substring(end - 50, end);
        } else {
            end = 50 - end;
            StringBuilder stringBuilder = new StringBuilder(50);
            for (int i = 0; i < end; ++i) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(result);
            result = stringBuilder.toString();
        }
        return result;
    }
}
