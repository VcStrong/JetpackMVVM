package com.vc.wd.common.util.logger;

import android.util.Log;

import com.vc.wd.common.util.logger.conf.LogConfiger;
import com.vc.wd.common.util.logger.printer.LogPrinter;

public final class Logger {

    public static Logger createLogger(String tag) {
        return new Logger(tag, LogConfiger.getDefaultPrinter());
    }

    public static Logger createLogger(Class cls) {
        return createLogger(cls.getName());
    }

    public static Logger createOnlineDebugLogger(String tag) {
        return new Logger(tag, LogConfiger.getOnlineDebugPrinter());
    }

    public static Logger createOnlineDebugLogger(Class cls) {
        return createOnlineDebugLogger(cls.getName());
    }

    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }

    private final String tag;
    private LogPrinter printer;

    private Logger(String tag, LogPrinter printer) {
        this.tag = tag;
        this.printer = printer;
    }

    public String getTag() {
        return tag;
    }

    public int v(String msg, Object... args) {
        return printer.v(tag, msg, args);
    }

    public int v(Throwable tr, String msg, Object... args) {
        return printer.v(tag, tr, msg, args);
    }

    public int v(Object msg) {
        return printer.v(tag, msg);
    }

    public int d(String msg, Object... args) {
        return printer.d(tag, msg, args);
    }

    public int d(Throwable tr, String msg, Object... args) {
        return printer.d(tag, tr, msg, args);
    }

    public int d(Object msg) {
        return printer.d(tag, msg);
    }

    public int i(String msg, Object... args) {
        return printer.i(tag, msg, args);
    }

    public int i(Throwable tr, String msg, Object... args) {
        return printer.i(tag, tr, msg, args);
    }

    public int i(Object msg) {
        return printer.i(tag, msg);
    }

    public int w(String msg, Object... args) {
        return printer.w(tag, msg, args);
    }

    public int w(Throwable tr, String msg, Object... args) {
        return printer.w(tag, tr, msg, args);
    }

    public int w(Throwable tr) {
        return printer.w(tag, tr);
    }

    public int w(Object msg) {
        return printer.w(tag, msg);
    }

    public int e(String msg, Object... args) {
        return printer.e(tag, msg, args);
    }

    public int e(Throwable tr, String msg, Object... args) {
        return printer.e(tag, tr, msg, args);
    }

    public int e(Object msg) {
        return printer.e(tag, msg);
    }
}
