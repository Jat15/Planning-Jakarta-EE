package com.pie.planingjakartaee.dao;

import com.pie.planingjakartaee.dao.entity.Role;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao implements Dao<Role> {

    private EntityManager em;
    public RoleDao() {
        em = Persistence.createEntityManagerFactory("PU").createEntityManager();
    }

    @Override
    public Optional<Role> get(int id) {
        Optional<Role> result = Optional.empty();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            result = Optional.of(em.find(Role.class, id));
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

    public Optional<Role> get(String name) {
        Optional<Role> result = Optional.empty();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            result = Optional.of(em.find(Role.class, name));
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
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();

        try {
            roles =  em.createQuery("select p from Role p", Role.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return roles;
    }

    @Override
    public void create(Role role) {
        /*EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(role);
            et.commit();
        } catch (Exception e){
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
         */
    }

    @Override
    public void update(Role role) {
        /*
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(role);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
         */
    }

    @Override
    public void delete(Role role) {
        /*
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.remove(em.contains(role) ? role : em.merge(role));
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
         */
    }
}
