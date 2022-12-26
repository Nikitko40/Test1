package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection CONNECTION = Util.getPostgreSQLConnectionJDBC();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement STATEMENT = CONNECTION.createStatement()) {
            STATEMENT.executeUpdate("drop table if exists USERS");
            STATEMENT.executeUpdate("CREATE TABLE users (id serial Primary key, name VARCHAR(30), lastName VARCHAR(30), age INT )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate("drop table if exists users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = CONNECTION.prepareStatement("insert into users (name, lastname, age) values (?,?,?) ")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            System.out.println("Пользователь: " + name + " добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate("delete from users where id =" + id);
            System.out.println("Пользователь удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;

        try (Statement statement = CONNECTION.createStatement();) {
            resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            for (User user : users) {
                System.out.println(user);
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate("truncate table users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
