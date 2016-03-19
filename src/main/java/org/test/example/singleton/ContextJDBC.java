package org.test.example.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

/**
 * Ussage example:
 * Connection connection = ContextJDBC.getInstance().getConnection().
  */
final public class ContextJDBC {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/DEMO";
    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection connection;


    private static void loadDriver() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
    }

    private static Connection openConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL,
                USER, // login
                PASS // password
        );
        connection.setAutoCommit(false);
        return connection;
    }

    private ContextJDBC() {
        try {
            loadDriver();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class ContextHelper {
        private static final ContextJDBC context = new ContextJDBC();
    }

    public static ContextJDBC getInstance() {
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
}
