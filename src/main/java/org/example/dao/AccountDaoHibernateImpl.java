package org.example.dao;

import java.util.List;

import org.example.entity.Account;
import org.example.handler.HibernateHandler;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDaoHibernateImpl implements AccountDao {

    @Override
    public void save(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Account findById(Long id) {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            return session.find(Account.class, id);
        }
    }

    @Override
    public List<Account> findAll() {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            return session.createQuery("from Account", Account.class).list();
        }
    }

    @Override
    public void update(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findByUserId(Long userId) {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            return session.createQuery("from Account a where a.user.id = :userId", Account.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }
}
