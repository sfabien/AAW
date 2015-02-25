/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author kk
 */
@Stateless
public class NotificationsSessionBean implements NotificationsSessionBeanLocal {

    @PersistenceContext(unitName="AAW-ejbPU",type=PersistenceContextType.TRANSACTION)
    private EntityManager em; 
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Notifications save(Notifications h) {
        h = em.merge(h);
        em.persist(h);
        return h;
    }

    @Override
    public void update(Notifications h) {
        em.merge(h);
    }

    @Override
    public void delete(Notifications h) {
        h = em.merge(h);
        em.remove(h);
    }

    @Override
    public Notifications find(long u) {
        return em.find(Notifications.class, u);
    }
}
