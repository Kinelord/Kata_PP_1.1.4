package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/kata_pp";
    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
//              Нужны в этом случае драйвера?
//            Driver driver = new com.mysql.cj.jdbc.Driver();
//            DriverManager.registerDriver(driver);
        return DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
    }
}
