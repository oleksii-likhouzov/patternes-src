package org.test.example.singleton;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

final public class ContextExample {
    private static volatile ContextExample context;
    private Properties props;

    private ContextExample() {
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

    public static ContextExample getInstance() {
        if (context == null) {
            synchronized (ContextExample.class) {
                if (context == null) {
                    context = new ContextExample();
                }
            }
        }
        return context;
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
