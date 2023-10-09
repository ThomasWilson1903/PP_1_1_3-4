package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/test_table";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "roottoor";
    private static SessionFactory sessionFactory = null;
    private static String driver = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("ОК");
        } catch (ClassNotFoundException e) {
            System.out.println("ErrorDriver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("ErrorSQL");
            System.out.println(e.getErrorCode());
            e.printStackTrace();
        }

        return connection;

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(AvailableSettings.DRIVER, driver);
                settings.put(AvailableSettings.URL, URL);
                settings.put(AvailableSettings.USER, USERNAME);
                settings.put(AvailableSettings.PASS, PASSWORD);
                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(AvailableSettings.SHOW_SQL, "true");

                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
