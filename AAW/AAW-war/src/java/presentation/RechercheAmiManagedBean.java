/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import dao.Message;
import dao.Utilisateur;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.AmisSessionBeanLocal;
import metier.EnvoieMessageSessionBeanLocal;


/**
 *
 * @author sfabien
 */
@ManagedBean(name="rechercheAmiManagedBean")
@RequestScoped
public class RechercheAmiManagedBean {

    @EJB
    AmisSessionBeanLocal amisService;
    
    private String message;
    private String nom;
    private String prenom;
    private ArrayList<Utilisateur> amis;
    
    public RechercheAmiManagedBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public ArrayList<Utilisateur> getAmis() {
        return amis;
    }

    public void setAmis(ArrayList<Utilisateur> amis) {
        this.amis = amis;
    }
    
    public void recherche(){
        amis = amisService.rechercheAmi(nom, prenom);
        message = "Recherche...";
    }
    
    public void envoieDemandeAmi() {
        message = "Demande envoyée !";
    }
}
