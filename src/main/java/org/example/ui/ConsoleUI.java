package org.example.ui;

import java.util.List;

import org.example.entity.User;
import org.example.service.UserService;

public class ConsoleUI {
    private final UserService userService;
    private final ConsoleReader reader;

    public ConsoleUI(UserService userService, ConsoleReader reader) {
        this.userService = userService;
        this.reader = reader;
    }

    public void start() {
        boolean run = true;

        while (run) {
            printCategory();
            int choice = reader.readInt("Choose option: ");

            switch (choice) {
                case 1 -> createUser();
                case 2 -> getUserByID();
                case 3 -> listAllUsers();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 6 -> getUsersWithHql();
                case 7 -> getUsersWithNativeQuery();
                case 8 -> getUsersWithCriteria();
                case 0 -> run = false;
                default -> System.out.println("Invalid option");
            }
        }

        reader.close();
    }

    public void printCategory() {
        System.out.println("\n========== User Service ==========");
        System.out.println("1. Create user");
        System.out.println("2. Get user by ID");
        System.out.println("3. List all users");
        System.out.println("4. Update user");
        System.out.println("5. Delete user");
        System.out.println("6. List users with HQL (age > 18)");
        System.out.println("7. List users with Native Query (age > 18)");
        System.out.println("8. List users with Criteria API (age > 18)");
        System.out.println("0. Exit");
    }

    private void createUser() {
        String name = reader.readString("Enter name: ");
        String email = reader.readString("Enter email: ");
        int age = reader.readInt("Enter age: ");

        userService.createUser(name, email, age);
        System.out.println("User created");
    }

    private void getUserByID() {
        Long id = reader.readLong("Enter user ID: ");

        User user = userService.getUser(id);
        System.out.println(user != null ? user : "User not found");
    }

    private void listAllUsers() {
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
    }

    private void updateUser() {
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

    private void deleteUser() {
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
