package com.vc.wd.common.util.logger.printer;

public class EmptyPrinter implements LogPrinter {
    @Override
    public int v(String tag, String msg, Object... args) {
        return 0;
    }

    @Override
    public int v(String tag, Throwable tr, String msg, Object... args) {
        return 0;
    }

    @Override
    public int d(String tag, String msg, Object... args) {
        return 0;
    }

    @Override
    public int d(String tag, Throwable tr, String msg, Object... args) {
        return 0;
    }

    @Override
    public int i(String tag, String msg, Object... args) {
        return 0;
    }

    @Override
    public int i(String tag, Throwable tr, String msg, Object... args) {
        return 0;
    }

    @Override
    public int w(String tag, String msg, Object... args) {
        return 0;
    }

    @Override
    public int w(String tag, Throwable tr, String msg, Object... args) {
        return 0;
    }

    @Override
    public int w(String tag, Throwable tr) {
        return 0;
    }

    @Override
    public int e(String tag, String msg, Object... args) {
        return 0;
    }

    @Override
    public int e(String tag, Throwable tr, String msg, Object... args) {
        return 0;
    }

    @Override
    public int v(String tag, Object msg) {
        return 0;
    }

    @Override
    public int d(String tag, Object msg) {
        return 0;
    }

    @Override
    public int i(String tag, Object msg) {
        return 0;
    }

    @Override
    public int w(String tag, Object msg) {
        return 0;
    }

    @Override
    public int e(String tag, Object msg) {
        return 0;
    }
}
