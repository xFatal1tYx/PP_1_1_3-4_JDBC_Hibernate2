package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aion";
    private static Connection connection;

    private static void initializeConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e + ". Ошибка соединения с базой данных!");
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            initializeConnection();
        }
        return connection;
    }


}

