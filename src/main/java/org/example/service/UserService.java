package org.example.service;

import java.util.List;

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
}
