package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    final static String URL = "jdbc:mysql://localhost:3306/test_table";
    final static String USER = "test_user";
    final static String PASSWORD = "password";
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("ОК");
        } catch (ClassNotFoundException e) {
            System.out.println("ErrorDriver");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("ErrorSQL");
            System.out.println(e.getErrorCode());
            throw new RuntimeException(e);
        }

        return connection;

    }
}
