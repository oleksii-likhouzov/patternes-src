package org.test.example.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Ussage example:
 * Connection connection = Context.getInstance().getConnection().
 */

public class Context {
    private static final String propertyFileName = "src/main/resources/config.properties";
    // JDBC driver name and database URL
    private static String dbJdbcDriver;
    private static String dbUrl;
    //  Database credentials
    private static String dbUser;
    private static String dbPassword;

    public static Connection connection;


    private static void loadDriver() throws ClassNotFoundException {
        Class.forName(dbJdbcDriver);
    }

    private static Connection openConnection() throws SQLException {
        connection = DriverManager.getConnection(dbUrl,
                dbUser,
                dbPassword
        );
        connection.setAutoCommit(false);
        return connection;
    }

    private static void loadProperties() throws IOException {
        try (FileInputStream fis = new FileInputStream(propertyFileName)) {
            Properties property = new Properties();
            property.load(fis);
            dbJdbcDriver = property.getProperty("JDBC_DRIVER");
            dbUrl = property.getProperty("DB_URL");
            dbUser = property.getProperty("USER");
            dbPassword = property.getProperty("PASSWORD");
        }
    }

    private Context() {
        try {
            try {
                loadProperties();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                loadDriver();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ContextHelper {
        private static final Context context = new Context();
    }

    public static Context getInstance() {
        return ContextHelper.context;
    }

    public synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = openConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public synchronized void closeConnection() throws SQLException {
        if (connection != null && connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
}
