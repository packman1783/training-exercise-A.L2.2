package org.example.dao;

import java.util.List;

import org.example.entity.User;

public interface UserDao {
    void save(User user);

    User findById(Long id);

    List<User> findAll();

    void update(User user);

    void delete(User user);

    List<User> getUsersWithHql();

    List<User> getUsersWithNativeQuery();

    List<User> getUsersWithCriteria();
}
