package org.example.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.dao.UserDao;
import org.example.dao.UserDaoHibernateImpl;
import org.example.entity.User;

public class UserServiceHibernateImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceHibernateImpl.class);

    private final UserDao userDao = new UserDaoHibernateImpl();

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

        logger.info("Fetched {} users", users.size());

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
}

