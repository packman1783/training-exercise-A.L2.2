package org.example.service;

import java.util.List;

import org.example.dao.UserDao;
import org.example.dao.UserDaoHibernateImpl;
import org.example.entity.User;

public class UserServiceHibernateImpl implements UserService {
    private final UserDao userDao = new UserDaoHibernateImpl();

    @Override
    public void createUser(String name, String email, int age) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        User user = new User(name, email, age);
        userDao.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void updateUser(Long id, String name, String email, int age) {
        User user = userDao.findById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setAge(age);
            userDao.update(user);
        } else {
            System.out.println("User not found");
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }
}

