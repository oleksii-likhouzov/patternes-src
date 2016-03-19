package org.test.threadsafe.singleton;

final public class Context {
    private static volatile Context context;

    private Context() {
    }

    public static Context getInstance() {
        if (context == null) {
            synchronized (Context.class) {
                if (context == null) {
                    context = new Context();
                }
            }
        }
        return context;
    }
}
