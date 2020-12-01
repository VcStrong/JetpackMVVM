package com.vc.wd.common.util.logger.printer;

import android.util.Log;

public interface LogPrinter {

    /**
     * Send a {@link Log#VERBOSE} log message.
     *
     * @param msg The message you would like logged.
     */
    int v(String tag, String msg, Object... args);

    /**
     * Send a {@link Log#VERBOSE} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    int v(String tag, Throwable tr, String msg, Object... args);

    /**
     * Send a {@link Log#VERBOSE} log message and log the exception.
     *
     * @param msg The message you would like logged.
     */
    int v(String tag, Object msg);

    /**
     * Send a {@link Log#DEBUG} log message.
     *
     * @param msg The message you would like logged.
     */
    int d(String tag, String msg, Object... args);

    /**
     * Send a {@link Log#DEBUG} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    int d(String tag, Throwable tr, String msg, Object... args);

    /**
     * Send a {@link Log#DEBUG} log message and log the exception.
     *
     * @param msg The message you would like logged.
     */
    int d(String tag, Object msg);

    /**
     * Send an {@link Log#INFO} log message.
     *
     * @param msg The message you would like logged.
     */
    int i(String tag, String msg, Object... args);

    /**
     * Send a {@link Log#INFO} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    int i(String tag, Throwable tr, String msg, Object... args);

    /**
     * Send a {@link Log#INFO} log message and log the exception.
     *
     * @param msg The message you would like logged.
     */
    int i(String tag, Object msg);

    /**
     * Send a {@link Log#WARN} log message.
     *
     * @param msg The message you would like logged.
     */
    int w(String tag, String msg, Object... args);

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    int w(String tag, Throwable tr, String msg, Object... args);

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     *
     * @param tr An exception to log
     */
    int w(String tag, Throwable tr);

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     *
     * @param msg The message you would like logged.
     */
    int w(String tag, Object msg);

    /**
     * Send an {@link Log#ERROR} log message.
     *
     * @param msg The message you would like logged.
     */
    int e(String tag, String msg, Object... args);

    /**
     * Send a {@link Log#ERROR} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    int e(String tag, Throwable tr, String msg, Object... args);

    /**
     * Send a {@link Log#ERROR} log message and log the exception.
     *
     * @param msg The message you would like logged.
     */
    int e(String tag, Object msg);
}
