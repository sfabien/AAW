/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.AnnuaireLocal;

/**
 *
 * @author sfabien
 */
@ManagedBean(name="rechercheListManagedBean")
@RequestScoped
public class RechercheListManagedBean {

    @EJB
    AnnuaireLocal service;
    /**
     * Creates a new instance of RechercheListManagedBean
     */
    public RechercheListManagedBean() {
    }
    
     public List<String> recherche(){
        FacesContext context = FacesContext.getCurrentInstance();  
       /* Etape 2: On récupère la requête courante (stockée dans le contexte) */
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       /* Etape 3: On récupère l'objet de type HttpSession*/
        HttpSession httpSession = request.getSession(true);
       /* Etape 4: On récupère l'attribut de type EJB stockée dans la session*/
        String id = (String) httpSession.getAttribute("id");
        return service.afficherContact(id);
    }
}
