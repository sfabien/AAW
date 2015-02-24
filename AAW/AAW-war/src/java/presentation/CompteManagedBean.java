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


/**
 *
 * @author sfabien
 */
@ManagedBean(name="suppresionCompteManagedBean")
@RequestScoped
public class CompteManagedBean {

    /*@EJB
    AnnuaireLocal service;
    
    private String id;
    private String mdp;
    
    public CompteManagedBean() {
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


    public AnnuaireLocal getService() {
        return service;
    }

    public void setService(AnnuaireLocal service) {
        this.service = service;
    }
    
    public boolean suppresion(){
        boolean test=false;
         FacesContext context = FacesContext.getCurrentInstance();  
       /* Etape 2: On récupère la requête courante (stockée dans le contexte) 
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       /* Etape 3: On récupère l'objet de type HttpSession
        HttpSession httpSession = request.getSession(true);
       /* Etape 4: On récupère l'attribut de type EJB stockée dans la session
        String idtmp=(String)httpSession.getAttribute("id");

       if(id.equals(idtmp))
            test=service.supprimerUser(id, mdp);
       if(test){
                httpSession.invalidate();
                return true;
       }
       return false;
    }*/
}
