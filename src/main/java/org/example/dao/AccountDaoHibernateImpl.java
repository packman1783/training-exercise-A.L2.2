package org.example.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.entity.Account;
import org.example.handler.HibernateHandler;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDaoHibernateImpl implements AccountDao {
    private static final Logger logger = LogManager.getLogger(AccountDaoHibernateImpl.class);

    @Override
    public void save(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(account);
            transaction.commit();

            logger.info("Account successfully saved: {}", account);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            logger.error("Error while saving account: {}", account, e);
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

            logger.info("Account successfully updated: {}", account);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            logger.error("Error while updating account: {}", account, e);
        }
    }

    @Override
    public void delete(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(account);
            transaction.commit();

            logger.info("Account successfully deleted: {}", account);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            logger.error("Error while deleting account: {}", account, e);
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
