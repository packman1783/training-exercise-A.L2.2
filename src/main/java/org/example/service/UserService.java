package org.example.service;

import java.util.List;

import org.example.entity.Account;
import org.example.entity.User;

public interface UserService {
    void createUser(String name, String email, int age);

    User getUser(Long id);

    List<User> getAllUsers();

    void updateUser(Long id, String name, String email, int age);

    void deleteUser(Long id);

    List<User> getUsersWithHql();

    List<User> getUsersWithNativeQuery();

    List<User> getUsersWithCriteria();

    void benchmarkQueries();

    void createAccountForUser(Long userId, Long accountNumber, Double balance);

    List<Account> getUserAccounts(Long userId);

    void deleteAccount(Long accountId);

    void transferMoney(Long fromAccountId, Long toAccountId, Double amount);
}
