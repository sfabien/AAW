/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Message;
import dao.MessageSessionBeanLocal;
import dao.Utilisateur;
import dao.UtilisateurSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
public class AmisSessionBean implements AmisSessionBeanLocal {
    
    @EJB
    UtilisateurSessionBeanLocal utilisateurDao;
    
    @PersistenceContext(unitName="AAW-ejbPU",type=PersistenceContextType.TRANSACTION)
    private EntityManager em; 
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public ArrayList<Utilisateur> rechercheAmi(String nom, String prenom) {
        return utilisateurDao.findByNameAndFirstname(nom, prenom);
    }
    
    @Override
    public boolean demandeAmi(Integer utilisateurDemandeEnAmi, Integer utilisateurQuiDemande) {
        Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,utilisateurQuiDemande);
        
        Query q2 = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,utilisateurDemandeEnAmi);
        
        if(!q.getResultList().isEmpty() && !q2.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            Utilisateur u2 = (Utilisateur) q2.getResultList().get(0);
            //u2.getDemandeAmis().add(u);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean accepteAmi(Integer utilisateurQuiAccepte, Integer utilisateurQuiDemande) {
        Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,utilisateurQuiAccepte);
        
        Query q2 = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,utilisateurQuiDemande);
        
        if(!q.getResultList().isEmpty() && !q2.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            Utilisateur u2 = (Utilisateur) q2.getResultList().get(0);
            //u.getDemandeAmis().remove(u2);
            //u2.getAmis().add(u);
            //u.getAmis().add(u2);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean refuseAmi(Integer utilisateurQuiRefuse, Integer utilisateurQuiDemande) {
        Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,utilisateurQuiRefuse);
        
        Query q2 = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,utilisateurQuiDemande);
        
        if(!q.getResultList().isEmpty() && !q2.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            Utilisateur u2 = (Utilisateur) q2.getResultList().get(0);
            //u.getDemandeAmis().remove(u2);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean supprimeAmi(Integer utilisateurQuiSupprime, Integer amiASupprimer) {
        Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,utilisateurQuiSupprime);
        
        Query q2 = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,amiASupprimer);
        
        if(!q.getResultList().isEmpty() && !q2.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            Utilisateur u2 = (Utilisateur) q2.getResultList().get(0);
            //u2.getAmis().remove(u);
            //u.getAmis().remove(u2);
            return true;
        }
        return false;
    }


}
