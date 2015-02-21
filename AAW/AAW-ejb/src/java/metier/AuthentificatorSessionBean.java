/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Utilisateur;
import dao.UtilisateurSessionBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author sfabien
 */
@Stateless
public class AuthentificatorSessionBean implements AuthentificatorSessionBeanLocal {

    @EJB
    UtilisateurSessionBeanLocal usbl;
    
    /*@PersistenceContext(unitName="AAW-ejbPU",type=PersistenceContextType.TRANSACTION)
    private EntityManager em; 
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }*/
    
    @Override
    public boolean creation(String id, String mdp, String nom, String prenom) {//TODO ....
        /*Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,id);*/

        //if(q.getResultList().isEmpty()){
            Utilisateur u = new Utilisateur(id,mdp,nom,prenom);
            usbl.save(u);
            return true;
        //}
        //return false;
    }
    
    @Override
    public boolean supprimer(String id, String mdp){
        /*Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,id);

        if(!q.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            if (u.getMdp().equals(mdp)){
                usbl.delete(u);
                return true;
            }
        }*/
        return false;
    }
    
    @Override
    public boolean connexion(String id, String mdp){
        /*Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,id);

        if(!q.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            if (u.getMdp().equals(mdp)){
                return true;
            }
        }*/
        
        return false;
    }
}
