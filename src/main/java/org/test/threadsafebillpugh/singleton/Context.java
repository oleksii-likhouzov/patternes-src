package org.test.threadsafebillpugh.singleton;

final public class Context {
    private Context() {
    }

    private static class ContextHelper {
        private static final Context context = new Context();
    }

    public static Context getInstance() {
        return ContextHelper.context;
    }
}
