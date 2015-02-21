/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    
    private String message;
    
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
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean creation() {
        boolean test=authService.creation(id, mdp, nom, prenom);
        if(!test){
            message="error";
            return false;
        }
        FacesContext context = FacesContext.getCurrentInstance();  
       /* Etape 2: On récupère la requête courante (stockée dans le contexte) */
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       /* Etape 3: On récupère l'objet de type HttpSession*/
        HttpSession httpSession = request.getSession(true);
       /* Etape 4: On récupère l'attribut de type EJB stockée dans la session*/
        httpSession.setAttribute("id", id);
        return true;
    }
}
