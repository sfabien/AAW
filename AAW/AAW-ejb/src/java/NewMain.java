
import dao.Utilisateur;
import dao.UtilisateurSessionBeanLocal;
import javax.ejb.EJB;
import metier.AmisSessionBeanLocal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kk
 */
public class NewMain {
    @EJB
    static UtilisateurSessionBeanLocal usbl;
    
    @EJB
    static AmisSessionBeanLocal asbl;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Utilisateur u1 = new Utilisateur("email1","motdepasse1","Fred");
        usbl.save(u1);
        
        Utilisateur u2 = new Utilisateur("email1","motdepasse1","Jack");
        usbl.save(u2);
        
        asbl.demandeAmi(u2.getIdUtilisateur(),u1.getIdUtilisateur());
        
        asbl.accepteAmi(u2.getIdUtilisateur(),u1.getIdUtilisateur());
        
        System.out.println(u2.getAmis().get(0).getNom());
    }
    
}
