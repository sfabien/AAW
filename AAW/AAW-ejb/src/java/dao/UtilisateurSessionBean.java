/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kk
 */
@Stateless
public class UtilisateurSessionBean implements UtilisateurSessionBeanLocal {

    @PersistenceContext(unitName="AAW-ejbPU")
    private EntityManager em; 
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    //@Transactional
    @Override
    public void save(Utilisateur h) {
        h = em.merge(h);
        em.persist(h);
    }

    @Override
    public void update(Utilisateur h) {
        em.merge(h);
    }

    @Override
    public void delete(Utilisateur h) {
        h = em.merge(h);
        em.remove(h);
    }
    
    @Override
    public Utilisateur getUtilisateur(String id){
        Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.email = ?").setParameter(1,id);

        if(!q.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            return u;
        }
        
        return null;
    }
}
