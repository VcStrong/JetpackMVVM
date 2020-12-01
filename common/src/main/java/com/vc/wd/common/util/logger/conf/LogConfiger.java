package com.vc.wd.common.util.logger.conf;

import com.vc.wd.common.util.logger.printer.ConsolePrinter;
import com.vc.wd.common.util.logger.printer.LogPrinter;

public class LogConfiger {

    private static final LogProxyPrinter DEF_LOG_PROXY = new LogProxyPrinter(new ConsolePrinter());
    private static final LogProxyPrinter ON_LOG_PROXY = new LogProxyPrinter(new ConsolePrinter());

    public static LogPrinter getDefaultPrinter() {
        return DEF_LOG_PROXY;
    }

    public static LogPrinter getOnlineDebugPrinter() {
        return ON_LOG_PROXY;
    }

    public static void setDefaultPrinter(LogPrinter logPrinter) {
        DEF_LOG_PROXY.setLogPrinter(logPrinter);
    }

    public static void setOnlineDebugPrinter(LogPrinter logPrinter) {
        ON_LOG_PROXY.setLogPrinter(logPrinter);
    }
}
