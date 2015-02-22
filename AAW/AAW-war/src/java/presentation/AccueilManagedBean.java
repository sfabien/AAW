/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import dao.Utilisateur;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.AuthentificatorSessionBeanLocal;
import metier.EnvoieMessageSessionBeanLocal;


/**
 *
 * @author sfabien
 */
@ManagedBean(name="accueilManagedBean")
@RequestScoped
public class AccueilManagedBean {

    @EJB
    AuthentificatorSessionBeanLocal authService;
    
    @EJB
    EnvoieMessageSessionBeanLocal envoieMessageService;
    
    private String message;
    
    public AccueilManagedBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getId() {
        return (String)getHttpSession().getAttribute("id");
    }

    public String getNom() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getNom();
    }

    public String getPrenom() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getPrenom();
    }

    public void envoieMessage(){
        Utilisateur u = authService.getUtilisateur((String)getHttpSession().getAttribute("id"));
        envoieMessageService.envoieMessagePublic(message,u,u);
        message = "";
    }
    
    private HttpSession getHttpSession() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        return request.getSession(true);
    }
    
}
