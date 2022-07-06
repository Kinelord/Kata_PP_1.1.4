package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kata_pp";
    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
    }
}
