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
import metier.AnnuaireLocal;

/**
 *
 * @author sfabien
 */
@ManagedBean(name="ajouterContactManagedBean")
@RequestScoped
public class AjouterContactManagedBean {

    @EJB
    AnnuaireLocal service;
    
    private String name;
    private String firstname;
    private String number;
    private String message;
    /**
     * Creates a new instance of AjouterContactManagedBean
     */
    public AjouterContactManagedBean() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public boolean ajouterContact(){
        FacesContext context = FacesContext.getCurrentInstance();  
       /* Etape 2: On récupère la requête courante (stockée dans le contexte) */
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       /* Etape 3: On récupère l'objet de type HttpSession*/
        HttpSession httpSession = request.getSession(true);
       /* Etape 4: On récupère l'attribut de type EJB stockée dans la session*/
        String id = (String) httpSession.getAttribute("id");
        boolean test=service.ajouterContact(id, name, firstname, number);
        if(!test){
            message="error";
        }
        return test;
    }
}
