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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.AuthentificatorSessionBeanLocal;
import metier.EnvoieMessageSessionBeanLocal;


/**
 *
 * @author sfabien
 */
@ManagedBean(name="profilAmiManagedBean")
public class ProfilAmiManagedBean {

    
    @EJB
    AuthentificatorSessionBeanLocal authService;
    
    @EJB
    EnvoieMessageSessionBeanLocal envoieMessageService;
    
    private String message;
    
    public ProfilAmiManagedBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getId() {
        return (String)getHttpSession().getAttribute("idAmi");
    }

    public Date getDate() {
        Calendar cal = Calendar.getInstance();
        return new Date(cal.getTimeInMillis());
    }
    
    public String getImage() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("idAmi")).getImage();
    }
    
    public String getNom() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("idAmi")).getNom();
    }

    public String getPrenom() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("idAmi")).getPrenom();
    }

    public ArrayList<Message> getMessages() {
        return envoieMessageService.mur(authService.getUtilisateur((String)getHttpSession().getAttribute("idAmi")));
    }
    
    public List<Utilisateur> getAmis() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("idAmi")).getAmis();
    }
    
    public void envoieMessage(){
        Utilisateur u = authService.getUtilisateur((String)getHttpSession().getAttribute("id"));
        Utilisateur ami = authService.getUtilisateur((String)getHttpSession().getAttribute("idAmi"));
        envoieMessageService.envoieMessagePublic(message,u,ami,null);
        message = "";
    }
    
    private HttpSession getHttpSession() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        return request.getSession(true);
    }
    
}
