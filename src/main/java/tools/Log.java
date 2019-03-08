package tools;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    public static final int LOG_LEVEL_DEBUG = 40;
    public static final int LOG_LEVEL_INFO = 30;
    public static final int LOG_LEVEL_WARN = 20;
    public static final int LOG_LEVEL_ERROR = 10;
    public static final int LOG_LEVEL_CRITICAL_ERROR = 0;
    private static int level = LOG_LEVEL_INFO;

    public static void debug(String message) {
        Log.log(LOG_LEVEL_DEBUG, message);
    }

    public static void error(String message) {
        Log.log(LOG_LEVEL_ERROR, message);
    }

    public static void warn(String message) {
        Log.log(LOG_LEVEL_WARN, message);
    }

    public static void info(String message) {
        Log.log(LOG_LEVEL_INFO, message);
    }

    private static void log(int level, String message) {

        if (level <= Log.level) {

            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            String caller = stackTraceElements[3].getClassName().replaceAll(".*\\.", "") + "." +
                    stackTraceElements[3].getMethodName()+ ":" +
                    stackTraceElements[3].getLineNumber();

            logger.info(new LogLevelNames().getName(level) + " " + caller + " - " + message);
        }
    }

    public static synchronized void setLogLevel(int level) {
        Log.level = level;
    }

    static class LogLevelNames {

        private Map<Integer, String> names = new HashMap<>();

        LogLevelNames() {

            names.put(LOG_LEVEL_DEBUG, "DEBUG");
            names.put(LOG_LEVEL_INFO, "INFO");
            names.put(LOG_LEVEL_WARN, "WARN");
            names.put(LOG_LEVEL_ERROR, "ERROR");
            names.put(LOG_LEVEL_CRITICAL_ERROR, "CRITICAL_ERROR");
        }

        public String getName(int index) {
            return names.containsKey(index) ? names.get(index) : "LEVEL_" + index;
        }
    }
}
