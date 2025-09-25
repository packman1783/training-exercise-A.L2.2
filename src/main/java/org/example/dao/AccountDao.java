package org.example.dao;

import java.util.List;

import org.example.entity.Account;

public interface AccountDao {
    void save(Account account);

    Account findById(Long id);

    List<Account> findAll();

    void update(Account account);

    void delete(Account account);

    List<Account> findByUserId(Long id);
}
