package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String tableName = "users";
    private Connection connect;
    // Почему удаляем поле Statement? Разве нельзя было его объявить?
    // Вопрос по открытию поля connect

    {
        try {
            connect = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createUsersTable() {
        String newTable = String.format("CREATE TABLE IF NOT EXISTS %s(user_id INT PRIMARY KEY AUTO_INCREMENT," + "name VARCHAR(30), lastName VARCHAR(50), age INT(3));", tableName);

        try (Statement statement = connect.createStatement()) {

            statement.execute(newTable);
            System.out.println("Таблица создана");


        } catch (SQLException e) {
            System.out.println("Ошибка, Таблица уже существует");
        }

    }

    public void dropUsersTable() {

        String dropTable = String.format("DROP TABLE IF EXISTS %s;", tableName);

        try (Statement statement = connect.createStatement()) {

            statement.execute(dropTable);
            System.out.println("Таблица удалена или ее не существовало");


        } catch (SQLException e) {
            System.out.println("Ошибка, Таблицы не существует");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertTable = String.format("INSERT INTO %s(name, lastName, age) " + "VALUES('%s', '%s', %d);", tableName, name, lastName, age);

        try (PreparedStatement preparedStatement = connect.prepareStatement(insertTable)) {
            preparedStatement.execute();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println("Ошибка записи в базу данных");
        }

    }

    public void removeUserById(long id) {
        String removeTable = String.format("DELETE FROM %s WHERE user_id = %d;", tableName, id);

        try (PreparedStatement preparedStatement = connect.prepareStatement(removeTable)) {
            preparedStatement.execute();
            System.out.printf("User с номером id - %s удален из базы данных\n", id);
        } catch (SQLException e) {
            System.out.println("Таблицы не существует");
        }

    }

    public List<User> getAllUsers() {

        List<User> listUser = new ArrayList<>();
        String query = String.format("SELECT * FROM %s;", tableName);
        try (Statement statement = connect.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("user_id"), resultSet.getString("name"), resultSet.getString(3), resultSet.getByte(4));
                System.out.println(user);
                listUser.add(user);
            }
        } catch (SQLException sqlE) {
            System.out.println("Ошибка вывода из базы данных");
        }
        return listUser;
    }

    public void cleanUsersTable() {
        String cleanTable = String.format("TRUNCATE TABLE %s;", tableName);

        try (Statement statement = connect.createStatement()) {
            statement.execute(cleanTable);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Очистка несработала");
        }

    }

    public void close() {
        try {
            connect.close();
            System.out.println("Соеденение закрыто");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
