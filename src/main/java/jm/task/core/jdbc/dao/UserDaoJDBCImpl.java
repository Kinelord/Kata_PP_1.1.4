package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String tableName = "users";
    private Util connect = new Util();
    private Statement statement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String newTable = String.format("CREATE TABLE %s(user_id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(30), lastName VARCHAR(50), age INT(3));", tableName);

        try {
            statement = connect.getConnection().createStatement();
            if (connect.tableExists(tableName)) {
                statement.execute(newTable);
                System.out.println("Таблица создана");
            }

        } catch (SQLException e) {
            System.out.println("Таблица уже существует");
        }

    }

    public void dropUsersTable() {

        String dropTable = String.format("DROP TABLE %s;", tableName);

        try {
            statement = connect.getConnection().createStatement();
            if (connect.tableExists(tableName)) {
                statement.execute(dropTable);
                System.out.println("Таблица удалена");
                connect.close();
            }

        } catch (SQLException e) {
            System.out.println("Таблицы не существует");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertTable = String.format("INSERT INTO %s(name, lastName, age) VALUES('%s', '%s', %d);", tableName, name, lastName, age);

        try {
            statement = connect.getConnection().createStatement();
            statement.execute(insertTable);
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println("Ошибка записи в базу данных");
        }

    }

    public void removeUserById(long id) {
        String removeTable = String.format("DELETE FROM %s WHERE user_id = %d;", tableName, id);

        try {
            statement = connect.getConnection().createStatement();
            statement.execute(removeTable);
            System.out.printf("User с номером id - %s удален из базы данных\n", id);
        } catch (SQLException e) {
            System.out.println("Таблицы не существует");
        }

    }

    public List<User> getAllUsers() {

        List<User> listUser = new ArrayList<>();
        String query = String.format("SELECT * FROM %s;", tableName);
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString(3),
                        resultSet.getByte(4));
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

        try {
            statement = connect.getConnection().createStatement();
            statement.execute(cleanTable);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Очистка несработала");
        }

    }
}
