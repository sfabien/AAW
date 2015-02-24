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

    @Override
    public ArrayList<Utilisateur> rechercheAmi(String nom, String prenom) {
        return utilisateurDao.findByNameAndFirstname(nom, prenom);
    }
    
    @Override
    public boolean demandeAmi(String utilisateurDemandeEnAmi, String utilisateurQuiDemande) {
        Utilisateur u = utilisateurDao.find(utilisateurQuiDemande);
        Utilisateur ami = utilisateurDao.find(utilisateurDemandeEnAmi);
        ami.addDemandeAmi(u);
        utilisateurDao.update(ami);
        return true;
    }
    
    @Override
    public boolean accepteAmi(String idAmi, String idUtilisateur) {
        Utilisateur u = utilisateurDao.find(idUtilisateur);
        Utilisateur ami = utilisateurDao.find(idAmi);
        ami.addAmi(u);
        utilisateurDao.update(ami);
        u.addAmi(ami);
        u.suppDemandeAmi(ami);
        utilisateurDao.update(u);
        return true;
    }
    
    @Override
    public boolean refuseAmi(Integer utilisateurQuiRefuse, Integer utilisateurQuiDemande) {
        /*Query q = em.createQuery(
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
        }*/
        return false;
    }
    
    @Override
    public boolean supprimeAmi(Integer utilisateurQuiSupprime, Integer amiASupprimer) {
        /*Query q = em.createQuery(
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
        }*/
        return false;
    }


}
