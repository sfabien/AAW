/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sfabien
 */
@Local
public interface AuthentificatorSessionBeanLocal {
    public boolean creation(String id, String mdp, String nom, String prenom) ;
    
    public boolean supprimer(String id, String mdp);
    
    public boolean connexion(String id, String mdp);
}
