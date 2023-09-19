package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public Connection getConnection() {
        try (Connection connection = DriverManager.getConnection("127.0.0.1", "test_user", "password")) {
            return connection;
        } catch (SQLException ignored) {

        }
        return null;

    }
}
