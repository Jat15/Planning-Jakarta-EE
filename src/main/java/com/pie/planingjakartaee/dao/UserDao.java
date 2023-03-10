package com.pie.planingjakartaee.dao;

import com.pie.planingjakartaee.dao.entity.User;
import com.pie.planingjakartaee.dao.jpa.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private EntityManager em;
    public UserDao() {
        em = JPAUtils.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Optional<User> get(int id) {
        Optional<User> result = Optional.empty();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            result = Optional.of(em.find(User.class, id));
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return result;
    }


    public Optional<User> getPassword(String email) {
        Optional<User> result = Optional.empty();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            //result = Optional.of(em.find(User.class, email));
            result = Optional.of(em.createQuery("SELECT p FROM User p WHERE p.email LIKE ?1 ", User.class).setParameter(1, email ).getSingleResult());
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            users =  em.createQuery("select p from User p", User.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return users;
    }

    public List<User> getAllByRole(int idRole) {
        List<User> users = new ArrayList<>();

        try {
            users =  em.createQuery("select p from User p where p.role.id < ?1", User.class).setParameter(1, idRole ).getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return users;
    }

    public List<User> getAllFind(int idRole, String search) {
        List<User> users = new ArrayList<>();

        try {
            users = em.createQuery(
                            "select p " +
                                    "from User p " +
                                    "where p.role.id < ?1 " +
                                    "and (" +
                                    "p.lastName like :search " +
                                    "or p.email like :search " +
                                    "or p.firstName like :search " +
                                    "or p.pseudo like :search" +
                                    ")"
                            , User.class)
                    .setParameter(1, idRole)
                    .setParameter("search", "%" + search + "%")
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return users;
    }

    @Override
    public void create(User user) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(user);
            et.commit();
        } catch (Exception e){
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(user);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(User user) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.remove(em.contains(user) ? user : em.merge(user));
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }
}
