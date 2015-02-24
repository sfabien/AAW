/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import dao.Message;
import dao.Utilisateur;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.AmisSessionBeanLocal;
import metier.AuthentificatorSessionBeanLocal;
import metier.EnvoieMessageSessionBeanLocal;


/**
 *
 * @author sfabien
 */
@ManagedBean(name="accueilManagedBean")
@ViewScoped
public class AccueilManagedBean {

    @EJB
    AuthentificatorSessionBeanLocal authService;
    
    @EJB
    EnvoieMessageSessionBeanLocal envoieMessageService;
    
    @EJB
    AmisSessionBeanLocal amisService;
    
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

    public Date getDate() {
        Calendar cal = Calendar.getInstance();
        return new Date(cal.getTimeInMillis());
    }
    
    public String getImage() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getImage();
    }
    
    public String getNom() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getNom();
    }

    public String getPrenom() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getPrenom();
    }

    public ArrayList<Message> getMessages() {
        return envoieMessageService.mur(authService.getUtilisateur((String)getHttpSession().getAttribute("id")));
    }
    
    public List<Utilisateur> getDemandesAmi() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getDemandeAmis();
    }
    
    public List<Utilisateur> getAmis() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getAmis();
    }
    
    public void envoieMessage(){
        Utilisateur u = authService.getUtilisateur((String)getHttpSession().getAttribute("id"));
        envoieMessageService.envoieMessagePublic(message,u,u);
        message = "";
    }
    
    public void accepterAmi(String idAmi) {
        amisService.accepteAmi(idAmi, (String)getHttpSession().getAttribute("id"));
    }
    
    private HttpSession getHttpSession() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        return request.getSession(true);
    }
    
}
