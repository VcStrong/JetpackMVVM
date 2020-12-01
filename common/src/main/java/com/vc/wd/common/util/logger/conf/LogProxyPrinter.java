package com.vc.wd.common.util.logger.conf;

import com.vc.wd.common.util.logger.printer.LogPrinter;

public class LogProxyPrinter implements LogPrinter {
    private LogPrinter logPrinter;

    public LogProxyPrinter(LogPrinter logPrinter) {
        this.logPrinter = logPrinter;
    }

    public void setLogPrinter(LogPrinter logPrinter) {
        this.logPrinter = logPrinter;
    }

    @Override
    public int v(String tag, String msg, Object... args) {
        return logPrinter.v(tag, msg, args);
    }

    @Override
    public int v(String tag, Throwable tr, String msg, Object... args) {
        return logPrinter.v(tag, tr, msg, args);
    }

    @Override
    public int v(String tag, Object msg) {
        return logPrinter.v(tag, msg);
    }

    @Override
    public int d(String tag, String msg, Object... args) {
        return logPrinter.d(tag, msg, args);
    }

    @Override
    public int d(String tag, Throwable tr, String msg, Object... args) {
        return logPrinter.d(tag, tr, msg, args);
    }

    @Override
    public int d(String tag, Object msg) {
        return logPrinter.d(tag, msg);
    }

    @Override
    public int i(String tag, String msg, Object... args) {
        return logPrinter.i(tag, msg, args);
    }

    @Override
    public int i(String tag, Throwable tr, String msg, Object... args) {
        return logPrinter.i(tag, tr, msg, args);
    }

    @Override
    public int i(String tag, Object msg) {
        return logPrinter.i(tag, msg);
    }

    @Override
    public int w(String tag, String msg, Object... args) {
        return logPrinter.w(tag, msg, args);
    }

    @Override
    public int w(String tag, Throwable tr, String msg, Object... args) {
        return logPrinter.w(tag, tr, msg, args);
    }

    @Override
    public int w(String tag, Throwable tr) {
        return logPrinter.w(tag, tr);
    }

    @Override
    public int w(String tag, Object msg) {
        return logPrinter.w(tag, msg);
    }

    @Override
    public int e(String tag, String msg, Object... args) {
        return logPrinter.e(tag, msg, args);
    }

    @Override
    public int e(String tag, Throwable tr, String msg, Object... args) {
        return logPrinter.e(tag, tr, msg, args);
    }

    @Override
    public int e(String tag, Object msg) {
        return logPrinter.e(tag, msg);
    }
}
