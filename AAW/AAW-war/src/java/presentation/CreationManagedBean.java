/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.AuthentificatorSessionBeanLocal;


/**
 *
 * @author sfabien
 */
@ManagedBean(name="creationManagedBean")
public class CreationManagedBean {

    @EJB
    AuthentificatorSessionBeanLocal authService;
    
    private String id;
    private String mdp;
    private String nom;
    private String prenom;
    
    private String messageInscription;
    private String messageConnexion;
    
    public CreationManagedBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getMessageInscription() {
        return messageInscription;
    }

    public void setMessageInscription(String message) {
        this.messageInscription = message;
    }

    public String getMessageConnexion() {
        return messageConnexion;
    }

    public void setMessageConnexion(String messageConnexion) {
        this.messageConnexion = messageConnexion;
    }
    
    public boolean creation() {
        boolean test=authService.creation(id, mdp, nom, prenom);
        mdp = "";
        nom = "";
        prenom= "";
        if(!test){
            id = "";
            messageInscription="Erreur : compte existant !";
            return false;
        }
        messageInscription="Compte créé avec succès ! Connectez-vous !";
        id = "";
        return true;
    }
    
    public boolean connexion(){
        boolean test=authService.connexion(id, mdp);
        mdp = "";
        nom = "";
        prenom= "";
        if(!test){
            id = "";
            messageConnexion="Erreur ! Identifiants incorrects.";
            return false;
        }
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("id", id);
        messageConnexion="Connexion réussie !";
        id = "";
        return true;
    }
}
