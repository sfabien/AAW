/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Message;
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
    public boolean accepteAmi(Integer utilisateurQuiAccepte, Integer utilisateurQuiDemande);
    public boolean refuseAmi(Integer utilisateurQuiRefuse, Integer utilisateurQuiDemande);
    public boolean supprimeAmi(Integer utilisateurQuiSupprime, Integer amiASupprimer) ;
    public ArrayList<Utilisateur> rechercheAmi(String nom, String prenom);
}
