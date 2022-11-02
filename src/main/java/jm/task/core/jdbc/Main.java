package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Степан", "Степанов", (byte) 25);
        userService.saveUser("Мария", "Викторова", (byte) 31);
        userService.saveUser("Маргарита", "Юрьева", (byte) 38);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
