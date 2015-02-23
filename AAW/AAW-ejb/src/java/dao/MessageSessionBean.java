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
import javax.persistence.Query;

/**
 *
 * @author kk
 */
@Stateless
public class MessageSessionBean implements MessageSessionBeanLocal {

    @PersistenceContext(unitName="AAW-ejbPU",type=PersistenceContextType.TRANSACTION)
    private EntityManager em; 
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Message save(Message h) {
        Calendar cal = Calendar.getInstance();
        h.setDateEnvoi(new Date(cal.getTimeInMillis()));
        h = em.merge(h);
        em.persist(h);
        return h;
    }

    @Override
    public void update(Message h) {
        em.merge(h);
    }

    @Override
    public void delete(Message h) {
        h = em.merge(h);
        em.remove(h);
    }

    @Override
    public ArrayList<Message> listByUser(Utilisateur u) {
        return new ArrayList<>(em.createQuery("SELECT m FROM Message m WHERE m.emetteur = :user OR m.recepteur = :user",Message.class).setParameter("user", u).getResultList());
    }
}
