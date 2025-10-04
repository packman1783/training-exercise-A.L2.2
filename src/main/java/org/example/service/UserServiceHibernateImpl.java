package org.example.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.dao.AccountDao;
import org.example.dao.UserDao;
import org.example.entity.Account;
import org.example.entity.User;
import org.example.handler.HibernateHandler;
import org.example.utilitys.QueryBenchmark;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceHibernateImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceHibernateImpl.class);

    private final UserDao userDao;
    private final AccountDao accountDao;

    public UserServiceHibernateImpl(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @Override
    public void createUser(String name, String email, int age) {
        if (email == null || email.isBlank()) {

            logger.error("Attempted to create user with empty email");

            throw new IllegalArgumentException("Email cannot be empty");
        }
        User user = new User(name, email, age);
        userDao.save(user);

        logger.info("User created: {}", user);
    }

    @Override
    public User getUser(Long id) {
        User user = userDao.findById(id);

        if (user != null) {
            logger.debug("User found with id={}", id);
        } else {
            logger.warn("User not found with id={}", id);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();

        if (logger.isInfoEnabled()) {
            logger.info("Fetched {} users", users.size());
        }

        return users;
    }

    @Override
    public void updateUser(Long id, String name, String email, int age) {
        User user = userDao.findById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setAge(age);
            userDao.update(user);

            logger.info("User updated: {}", user);
        } else {
            logger.warn("Update failed, user not found with id={}", id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);

            logger.info("User deleted with id={}", id);
        } else {
            logger.warn("Delete failed, user not found with id={}", id);
        }
    }

    @Override
    public List<User> getUsersWithHql() {
        List<User> users = userDao.getUsersWithHql();

        logger.info("Fetched {} users with HQL", users.size());

        return users;
    }

    @Override
    public List<User> getUsersWithNativeQuery() {
        List<User> users = userDao.getUsersWithNativeQuery();

        logger.info("Fetched {} users with Native SQL", users.size());

        return users;
    }

    @Override
    public List<User> getUsersWithCriteria() {
        List<User> users = userDao.getUsersWithCriteria();

        logger.info("Fetched {} users with Criteria API", users.size());

        return users;
    }

    @Override
    public void benchmarkQueries() {
        logger.info("Starting query performance benchmark...");

        QueryBenchmark benchmark = new QueryBenchmark(userDao);
        benchmark.getQueryPerformance();
    }

    @Override
    public void createAccountForUser(Long userId, Double balance) {
        User user = userDao.findById(userId);
        if (user != null) {
            Account account = new Account(balance, user);
            accountDao.save(account);

            logger.info("Account created for user {}: {}", userId, account);
        } else {
            logger.warn("Cannot create account - user not found with id={}", userId);

            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public List<Account> getUserAccounts(Long userId) {
        List<Account> accounts = accountDao.findByUserId(userId);

        logger.info("Fetched {} accounts for user {}", accounts.size(), userId);

        return accounts;
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountDao.findById(accountId);
        if (account != null) {
            accountDao.delete(account);

            logger.info("Account deleted with id={}", accountId);
        } else {
            logger.warn("Delete failed, account not found with id={}", accountId);
        }
    }

    @Override
    public void transferMoney(Long fromAccountId, Long toAccountId, Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateHandler.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Account fromAccount = session.find(Account.class, fromAccountId);
            Account toAccount = session.find(Account.class, toAccountId);

            if (fromAccount == null || toAccount == null) {
                throw new IllegalArgumentException("One or both accounts not found");
            }

            double fromBalance = fromAccount.getBalance();
            double toBalance = toAccount.getBalance();

            if (fromBalance < amount) {
                throw new IllegalArgumentException("Result of transaction is negative");
            }

            fromAccount.setBalance(fromBalance - amount);
            toAccount.setBalance(toBalance + amount);

            session.merge(fromAccount);
            session.merge(toAccount);

            transaction.commit();

            logger.info("Transfer completed: {} from account {} to account {}", amount, fromAccountId, toAccountId);

            System.out.println("Transfer completed successfully");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            logger.error("Transfer failed: {}", e.getMessage(), e);

            System.err.println("Transfer failed: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}

