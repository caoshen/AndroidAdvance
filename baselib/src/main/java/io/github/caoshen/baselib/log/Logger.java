package io.github.caoshen.baselib.log;

public abstract class Logger {

    private static final String TAG = "BASELIB";

    public interface Factory {
        Logger getLoggerInstance(String category);
    }

    private static final class DefaultFactory implements Factory {

        @Override
        public Logger getLoggerInstance(String category) {
            return new DefaultLogger(category);
        }
    }

    private static Factory ourFactory = new DefaultFactory();

    public static Logger getInstance(Class<?> cl) {
        return ourFactory.getLoggerInstance("#" + cl.getName());
    }

    public abstract boolean isDebugEnabled();

    public abstract void debug(String message);

    public abstract void debug(String message, Throwable t);

    public void warn(String message) {
        warn(message, null);
    }

    public void warn(Throwable t) {
        warn(t.getMessage(), t);
    }

    public abstract void warn(String message, Throwable t);

}
