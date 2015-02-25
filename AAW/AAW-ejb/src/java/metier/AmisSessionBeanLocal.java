/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Utilisateur;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author kk
 */
@Local
public interface AmisSessionBeanLocal {
    public boolean demandeAmi(String utilisateurDemandeEnAmi, String utilisateurQuiDemande);
    public boolean accepteAmi(String idAmi, String idUtilisateur);
    public boolean refuseAmi(String utilisateurQuiRefuse, String utilisateurQuiDemande);
    public boolean supprimeAmi(String utilisateurQuiSupprime, String amiASupprimer) ;
    public ArrayList<Utilisateur> rechercheAmi(String nom, String prenom);
}
