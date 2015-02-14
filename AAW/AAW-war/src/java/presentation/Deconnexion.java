/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sfabien
 */
@ManagedBean(name="deconnexion")
@RequestScoped
public class Deconnexion {

    /**
     * Creates a new instance of Deconnexion
     */
    public Deconnexion() {
    }
    
    public boolean deconnexion(){
       FacesContext context = FacesContext.getCurrentInstance();  
       HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       HttpSession httpSession = request.getSession(true);
       httpSession.invalidate();
       return true;
    }
}
