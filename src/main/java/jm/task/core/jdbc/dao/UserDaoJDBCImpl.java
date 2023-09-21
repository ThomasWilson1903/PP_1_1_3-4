package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE `test_table`.`Users` (\n" +
                "  `idUsers` INT NOT NULL AUTO_INCREMENT ,\n" +
                "  `Name` VARCHAR(45) NOT NULL,\n" +
                "  `NameLast` VARCHAR(45) NOT NULL,\n" +
                "  `Age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`idUsers`));";
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE `test_table`.`Users`;";
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT INTO `test_table`.`Users` (`Name`, `NameLast`, `Age`) VALUES ('%s', '%s', '%d');", name, lastName, age);
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
