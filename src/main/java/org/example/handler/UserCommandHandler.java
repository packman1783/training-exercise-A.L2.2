package org.example.handler;

import java.util.List;

import org.example.entity.User;
import org.example.service.UserService;
import org.example.ui.ConsoleReader;

public class UserCommandHandler {
    private final UserService userService;
    private final ConsoleReader reader;

    public UserCommandHandler(UserService userService, ConsoleReader reader) {
        this.userService = userService;
        this.reader = reader;
    }

    public void createUser() {
        String name = reader.readString("Enter name: ");
        String email = reader.readString("Enter email: ");
        int age = reader.readInt("Enter age: ");

        userService.createUser(name, email, age);
        System.out.println("User created");
    }

    public void getUserByID() {
        Long id = reader.readLong("Enter user ID: ");
        User user = userService.getUser(id);
        System.out.println(user != null ? user : "User not found");
    }

    public void listAllUsers() {
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
    }

    public void updateUser() {
        Long id = reader.readLong("Enter user ID to update: ");
        User user = userService.getUser(id);

        if (user != null) {
            String name = reader.readString("New name (" + user.getName() + "): ");
            String email = reader.readString("New email (" + user.getEmail() + "): ");
            int age = reader.readInt("New age (" + user.getAge() + "): ");

            userService.updateUser(id, name, email, age);
            System.out.println("User updated");
        } else {
            System.out.println("User not found");
        }
    }

    public void deleteUser() {
        Long id = reader.readLong("Enter user ID to delete: ");
        userService.deleteUser(id);
        System.out.println("User deleted");
    }

    public void getUsersWithHql() {
        List<User> users = userService.getUsersWithHql();
        users.forEach(System.out::println);
    }

    public void getUsersWithNativeQuery() {
        List<User> users = userService.getUsersWithNativeQuery();
        users.forEach(System.out::println);
    }

    public void getUsersWithCriteria() {
        List<User> users = userService.getUsersWithCriteria();
        users.forEach(System.out::println);
    }
}
