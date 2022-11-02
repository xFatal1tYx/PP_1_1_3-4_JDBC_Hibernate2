package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aion";
    private static Connection connection;
    private static SessionFactory sessionFactory;

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

    private static void initializeSessionFactory() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initializeSessionFactory();
        }
        return sessionFactory;
    }

}

