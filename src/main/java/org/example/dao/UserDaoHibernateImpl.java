package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

import org.example.entity.User;
import org.example.handler.HibernateHandler;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
    @Override
    public void save(User user) {
        Transaction transaction = null;

        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            return session.find(User.class, id);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;

        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;

        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsersWithHql() {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            String hql = "FROM User u WHERE u.age > :age";

            return session.createQuery(hql, User.class)
                    .setParameter("age", 18)
                    .list();
        }
    }

    @Override
    public List<User> getUsersWithNativeQuery() {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            String sql = "SELECT * FROM users WHERE age > :age";

            return session.createNativeQuery(sql, User.class)
                    .setParameter("age", 18)
                    .list();
        }
    }

    @Override
    public List<User> getUsersWithCriteria() {
        try (Session session = HibernateHandler.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.greaterThan(root.get("age"), 18));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
