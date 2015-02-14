/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.AnnuaireLocal;

/**
 *
 * @author sfabien
 */
@ManagedBean(name="rechercheContactManagedBean")
@RequestScoped
public class RechercheContactManagedBean {

    @EJB
    AnnuaireLocal service;
    
    private String nom;
    
    public RechercheContactManagedBean() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public AnnuaireLocal getService() {
        return service;
    }

    public void setService(AnnuaireLocal service) {
        this.service = service;
    }
    
    public String recherche(){
        FacesContext context = FacesContext.getCurrentInstance();  
       /* Etape 2: On récupère la requête courante (stockée dans le contexte) */
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       /* Etape 3: On récupère l'objet de type HttpSession*/
        HttpSession httpSession = request.getSession(true);
       /* Etape 4: On récupère l'attribut de type EJB stockée dans la session*/
        String id = (String) httpSession.getAttribute("id");
        return service.rechercherContact(id, nom);
    }
}
