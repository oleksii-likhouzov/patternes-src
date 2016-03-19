package org.test.example.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by stalker on 13.03.16.
 */
final public class ContextInner {
    private Properties props;
    private ContextInner() {
        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(
                    new File("props.txt"));
            props.load(fis);
        }
        catch (Exception e) {
            // обработайте ошибку чтения конфигурации
        }
    }

    private static class ContextHelper {
        private static final ContextInner context = new ContextInner();
    }

    public static ContextInner getInstance() {
        return ContextHelper.context;
    }
    public synchronized String getProperty(String key) {
        String value = null;
        if (props.containsKey(key))
            value = (String) props.get(key);
        else {
            // сообщите о том, что свойство не найдено
        }
        return value;
    }
    // String propValue = Configuration.getInstance().getProperty(propKey).
}
