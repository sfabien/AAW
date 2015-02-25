/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Utilisateur;
import dao.UtilisateurSessionBeanLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
    public boolean refuseAmi(String utilisateurQuiRefuse, String utilisateurQuiDemande) {
        Utilisateur u = utilisateurDao.find(utilisateurQuiRefuse);
        Utilisateur ami = utilisateurDao.find(utilisateurQuiDemande);
        u.getDemandeAmis().remove(ami);
        utilisateurDao.update(u);
        return true;
    }
    
    @Override
    public boolean supprimeAmi(String utilisateurQuiSupprime, String amiASupprimer) {
        Utilisateur u = utilisateurDao.find(utilisateurQuiSupprime);
        Utilisateur ami = utilisateurDao.find(amiASupprimer);
        ami.getAmis().remove(u);
        u.getAmis().remove(ami);
        utilisateurDao.update(ami);
        utilisateurDao.update(u);
        return true;
    }


}
