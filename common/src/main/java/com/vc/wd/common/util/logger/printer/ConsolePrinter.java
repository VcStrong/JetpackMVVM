package com.vc.wd.common.util.logger.printer;

import android.util.Log;

import java.util.Locale;

public class ConsolePrinter implements LogPrinter {

    public ConsolePrinter() {
    }

    @Override
    public int v(String tag, String msg, Object... args) {
        return Log.v(tag, format(msg, args));
    }

    @Override
    public int v(String tag, Throwable tr, String msg, Object... args) {
        return Log.v(tag, format(msg, args), tr);
    }

    @Override
    public int d(String tag, String msg, Object... args) {
        return Log.d(tag, format(msg, args));
    }

    @Override
    public int d(String tag, Throwable tr, String msg, Object... args) {
        return Log.d(tag, format(msg, args), tr);
    }

    @Override
    public int i(String tag, String msg, Object... args) {
        return Log.i(tag, format(msg, args));
    }

    @Override
    public int i(String tag, Throwable tr, String msg, Object... args) {
        return Log.i(tag, format(msg, args), tr);
    }

    @Override
    public int w(String tag, String msg, Object... args) {
        return Log.w(tag, format(msg, args));
    }

    @Override
    public int w(String tag, Throwable tr, String msg, Object... args) {
        return Log.w(tag, format(msg, args), tr);
    }

    @Override
    public int w(String tag, Throwable tr) {
        return Log.w(tag, tr);
    }

    @Override
    public int e(String tag, String msg, Object... args) {
        return Log.e(tag, format(msg, args));
    }

    @Override
    public int e(String tag, Throwable tr, String msg, Object... args) {
        return Log.e(tag, format(msg, args), tr);
    }

    private String format(String msg, Object... args) {
        return String.format(Locale.CHINA, msg, args);
    }

    @Override
    public int v(String tag, Object msg) {
        return Log.v(tag, msg.toString());
    }

    @Override
    public int d(String tag, Object msg) {
        return Log.d(tag, msg.toString());
    }

    @Override
    public int i(String tag, Object msg) {
        return Log.i(tag, msg.toString());
    }

    @Override
    public int w(String tag, Object msg) {
        return Log.w(tag, msg.toString());
    }

    @Override
    public int e(String tag, Object msg) {
        return Log.e(tag, msg.toString());
    }
}
