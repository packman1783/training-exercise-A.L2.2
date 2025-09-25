package org.example.ui;

import org.example.handler.AccountCommandHandler;
import org.example.handler.BenchmarkCommandHandler;
import org.example.handler.UserCommandHandler;
import org.example.service.UserService;
import org.example.timeUtility.QueryBenchmark;

public class ConsoleUI {
    private final ConsoleReader reader;
    private final UserCommandHandler userHandler;
    private final AccountCommandHandler accountHandler;
    private final BenchmarkCommandHandler benchmarkHandler;

    public ConsoleUI(UserService userService, ConsoleReader reader, QueryBenchmark queryBenchmark) {
        this.reader = reader;
        this.userHandler = new UserCommandHandler(userService, reader);
        this.accountHandler = new AccountCommandHandler(userService, reader);
        this.benchmarkHandler = new BenchmarkCommandHandler(queryBenchmark);
    }

    public void start() {
        boolean run = true;

        while (run) {
            printCategory();
            int choice = reader.readInt("Choose option: ");

            switch (choice) {
                case 1 -> userHandler.createUser();
                case 2 -> userHandler.getUserByID();
                case 3 -> userHandler.listAllUsers();
                case 4 -> userHandler.updateUser();
                case 5 -> userHandler.deleteUser();
                case 6 -> userHandler.getUsersWithHql();
                case 7 -> userHandler.getUsersWithNativeQuery();
                case 8 -> userHandler.getUsersWithCriteria();
                case 9 -> benchmarkHandler.getQueryPerformance();
                case 10 -> accountHandler.createAccountForUser();
                case 11 -> accountHandler.getUserAccounts();
                case 12 -> accountHandler.deleteAccount();
                case 13 -> accountHandler.transferMoney();
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
        System.out.println("9. Get query performance for HQL Native Criteria request");
        System.out.println("10. Create account for user");
        System.out.println("11. Get user accounts");
        System.out.println("12. Delete account");
        System.out.println("13. Transfer money between accounts");
        System.out.println("0. Exit");
    }
}
