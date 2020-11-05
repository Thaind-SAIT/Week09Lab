/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;
/**
 *
 * @author 808278
 */
public class UserDB {
    
    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.createNamedQuery("User.findAll", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    public User getUser(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        User user = em.find(User.class, username);
        return user;
    }

    public void delete(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            User user = em.find(User.class, username);
            trans.begin();
            em.remove( em.merge(user) );
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
