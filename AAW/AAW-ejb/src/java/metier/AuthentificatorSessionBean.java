/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Utilisateur;
import dao.UtilisateurSessionBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sfabien
 */
@Stateless
public class AuthentificatorSessionBean implements AuthentificatorSessionBeanLocal {

    @EJB
    UtilisateurSessionBeanLocal utilisateurDao;
    
    @Override
    public boolean creation(String id, String mdp, String nom, String prenom) {
        Utilisateur u = utilisateurDao.find(id);
        if(u==null) {
            u = new Utilisateur(id,mdp,nom,prenom);
            utilisateurDao.save(u);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean supprimer(String id, String mdp){
        return false;
    }
    
    @Override
    public boolean connexion(String id, String mdp){
        Utilisateur u = utilisateurDao.find(id);
        if(u!=null) {
            return (u.getMdp().equals(mdp));
        }
        return false;
    }

    @Override
    public Utilisateur getUtilisateur(String id) {
        return utilisateurDao.find(id);
    }
}
