package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public Connection getConnection() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/store", "root", "password");) {
            return connection;
        } catch (SQLException ex) {

        }
        return null;

    }
}
