package com.vc.wd.common.util.logger.printer;

import android.content.Context;
import android.util.Log;

import java.util.Locale;

public class FileLogPrinter implements LogPrinter {


    private static FileLogger fileLogger = new FileLogger();

    public static void init(String path, Context ctx) {
        fileLogger.init(path, ctx);
    }

    public static void flush() {
        fileLogger.flush();
    }

    public FileLogPrinter() {
    }

    @Override
    public int v(String tag, String msg, Object... args) {
        fileLogger.write(Log.VERBOSE, tag, this.format(msg, args));
        return 0;
    }

    @Override
    public int v(String tag, Throwable throwable, String msg, Object... args) {
        fileLogger.write(Log.VERBOSE, tag, this.format(msg, args));
        return 0;
    }

    @Override
    public int v(String tag, Object msg) {
        fileLogger.write(Log.VERBOSE, tag, String.valueOf(msg));
        return 0;
    }

    @Override
    public int d(String tag, String msg, Object... args) {
        fileLogger.write(Log.DEBUG, tag, this.format(msg, args));
        return 0;
    }

    @Override
    public int d(String tag, Throwable throwable, String msg, Object... args) {
        fileLogger.write(Log.DEBUG, tag, this.format(msg, args) + "\r\n" + Log.getStackTraceString(throwable));
        return 0;
    }

    @Override
    public int d(String tag, Object msg) {
        fileLogger.write(Log.DEBUG, tag, String.valueOf(msg));
        return 0;
    }


    @Override
    public int i(String tag, String msg, Object... args) {
        fileLogger.write(Log.INFO, tag, this.format(msg, args));
        return 0;
    }

    @Override
    public int i(String tag, Throwable throwable, String msg, Object... args) {
        fileLogger.write(Log.INFO, tag, this.format(msg, args) + "\r\n" + Log.getStackTraceString(throwable));
        return 0;
    }

    @Override
    public int i(String tag, Object msg) {
        fileLogger.write(Log.INFO, tag, String.valueOf(msg));
        return 0;
    }

    @Override
    public int w(String tag, String msg, Object... args) {
        fileLogger.write(Log.WARN, tag, this.format(msg, args));
        return 0;
    }

    @Override
    public int w(String tag, Throwable throwable, String msg, Object... args) {
        fileLogger.write(Log.WARN, tag, this.format(msg, args) + "\r\n" + Log.getStackTraceString(throwable));
        return 0;
    }

    @Override
    public int w(String tag, Throwable throwable) {
        fileLogger.write(Log.WARN, tag, Log.getStackTraceString(throwable));
        return 0;
    }

    @Override
    public int w(String tag, Object msg) {
        fileLogger.write(Log.WARN, tag, String.valueOf(msg));
        return 0;
    }

    @Override
    public int e(String tag, String msg, Object... args) {
        fileLogger.write(Log.ERROR, tag, this.format(msg, args));
        return 0;
    }

    @Override
    public int e(String tag, Throwable throwable, String msg, Object... args) {
        fileLogger.write(Log.ERROR, tag, this.format(msg, args) + "\r\n" + Log.getStackTraceString(throwable));
        return 0;
    }

    @Override
    public int e(String tag, Object msg) {
        fileLogger.write(Log.ERROR, tag, String.valueOf(msg));
        return 0;
    }

    private String format(String msg, Object... args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i] == null ? "null" : args[i];
        }
        return String.format(Locale.CHINA, msg, args);
    }
}
