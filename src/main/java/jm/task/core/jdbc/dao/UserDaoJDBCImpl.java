package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE `test_table`.`Users` (\n" +
                "  `idUsers` INT NOT NULL AUTO_INCREMENT ,\n" +
                "  `Name` VARCHAR(45) NOT NULL,\n" +
                "  `NameLast` VARCHAR(45) NOT NULL,\n" +
                "  `Age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`idUsers`));";
        Connection connection = Util.getConnection();

        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String tableNameToCheck = "test_table";
        try {
            ResultSet tables = metaData.getTables(null, null, tableNameToCheck, null);
            if (!tables.next()) {
                try {
                    Statement statement = connection.createStatement();
                    statement.execute(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE Users;";

        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT INTO Users (`Name`, `NameLast`, `Age`) VALUES ('%s', '%s', '%d');", name, lastName, age);
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM Users WHERE (`idUsers` = '%d');", id);
        Connection connection = Util.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        Connection connection = Util.getConnection();
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("Name"),
                        resultSet.getString("NameLast"),
                        resultSet.getByte("Age")
                ));
            }
        } catch (SQLException e) {
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";
        Connection connection = Util.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
        }
    }
}
