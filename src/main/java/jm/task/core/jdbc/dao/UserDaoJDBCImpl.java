package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        String comm = "CREATE TABLE IF NOT EXISTS Users (id BIGINT NOT NULL AUTO_INCREMENT, name CHAR(50) NOT NULL, lastName CHAR(50) NOT NULL, age TINYINT NOT NULL, primary key (id));";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(comm);
        } catch (SQLException e) {
            throw new RuntimeException(e + ". Ошибка создании новой таблицы!");
        }
    }

    public void dropUsersTable() {
        String comm = "DROP TABLE IF EXISTS Users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(comm);
        } catch (SQLException e) {
            throw new RuntimeException(e + ". Ошибка удаления таблицы!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String comm = "INSERT INTO Users (name, lastName, age) VALUES (?,?,?)";
        try (PreparedStatement preState = Util.getConnection().prepareStatement(comm)) {
            preState.setString(1, name);
            preState.setString(2, lastName);
            preState.setByte(3, age);
            preState.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e + ". Ошибка добавления пользователя!");
        }
    }

    public void removeUserById(long id) {
        String comm = "DELETE Users FROM Users WHERE id = " + id;
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(comm);
        } catch (SQLException e) {
            throw new RuntimeException(e + ". Ошибка удаления пользователя!");
        }
    }

    public List<User> getAllUsers() {
        String comm = "SELECT * FROM Users";
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(comm);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e + ". Ошибка получения списка пользователей!");
        }
        return list;
    }

    public void cleanUsersTable() {
        String comm = "TRUNCATE TABLE Users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(comm);
        } catch (SQLException e) {
            throw new RuntimeException(e + ". Ошибка очищения таблицы!");
        }
    }
}
