package org.example.handler;

import java.util.List;

import org.example.entity.Account;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.ui.ConsoleReader;

public class AccountCommandHandler {
    private final UserService userService;
    private final ConsoleReader reader;

    public AccountCommandHandler(UserService userService, ConsoleReader reader) {
        this.userService = userService;
        this.reader = reader;
    }

    public void createAccountForUser() {
        Long userId = reader.readLong("Enter user ID: ");

        User user = userService.getUser(userId);
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found");
            return;
        }

        Double balance = reader.readDouble("Enter initial balance: ");

        userService.createAccountForUser(userId, balance);
        System.out.println("Account created successfully");
    }

    public void getUserAccounts() {
        Long userId = reader.readLong("Enter user ID: ");
        List<Account> accounts = userService.getUserAccounts(userId);

        if (accounts.isEmpty()) {
            System.out.println("No accounts found for this user");
        } else {
            accounts.forEach(System.out::println);
        }
    }

    public void deleteAccount() {
        Long accountId = reader.readLong("Enter account ID to delete: ");
        userService.deleteAccount(accountId);
        System.out.println("Account deleted successfully");
    }

    public void transferMoney() {
        Long fromAccountId = reader.readLong("Enter source account ID: ");
        Long toAccountId = reader.readLong("Enter destination account ID: ");
        Double amount = reader.readDouble("Enter amount to transfer: ");

        userService.transferMoney(fromAccountId, toAccountId, amount);
        System.out.println("Transfer completed successfully");
    }
}
