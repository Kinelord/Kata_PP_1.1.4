package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Roman", "Black", (byte) 13);
        userService.saveUser("Oleg", "Fulba", (byte) 18);
        userService.saveUser("Adrianno", "Meressy", (byte) 21);
        userService.getAllUsers();
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.dropUsersTable();
    }
}
