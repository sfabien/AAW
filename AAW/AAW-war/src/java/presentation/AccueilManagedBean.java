/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import dao.Message;
import dao.Notifications;
import dao.Utilisateur;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
    
        Cloudinary cloudinary = new Cloudinary(Cloudinary.asMap(
            "cloud_name", "df6myek6a",
            "api_key", "137416967793742",
            "api_secret", "ew-lrT6DyApJgVRUlO5yILg7dac"));
    
    private String message;
    private Part file;
    private String imagePub;

    public String getImagePub() {
        return imagePub;
    }

    public void setImagePub(String imagePub) {
        this.imagePub = imagePub;
        changeImage();
    }
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
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
    
    public List<Notifications> getNotifications() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getNotifications();
    }
    
    public void notificationsLu(Long n) {
        authService.notificationLu((String)getHttpSession().getAttribute("id"),n);
    }
    
    public List<Utilisateur> getDemandesAmi() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getDemandeAmis();
    }
    
    public List<Utilisateur> getAmis() {
        return authService.getUtilisateur((String)getHttpSession().getAttribute("id")).getAmis();
    }
    
    public void envoieMessage(){
        Utilisateur u = authService.getUtilisateur((String)getHttpSession().getAttribute("id"));
        if(file != null)
            changeImage();
        file = null;
        envoieMessageService.envoieMessagePublic(message,u,u,imagePub);
        imagePub = null;
        message = "";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("accueil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AccueilManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void envoieMessageProfil(){
        Utilisateur u = authService.getUtilisateur((String)getHttpSession().getAttribute("id"));
        if(file != null)
            changeImage();
        file = null;
        envoieMessageService.envoieMessagePublic(message,u,u,imagePub);
        imagePub = null;
        message = "";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("profil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AccueilManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void accepterAmi(String idAmi) {
        amisService.accepteAmi(idAmi, (String)getHttpSession().getAttribute("id"));
    }
    
    public void refuserAmi(String idAmi) {
        amisService.refuseAmi((String)getHttpSession().getAttribute("id"),idAmi);
    }
    
    public void changeImage() {
         try {
            byte[] results = new byte[(int) file.getSize()];
            InputStream in = file.getInputStream();
            in.read(results);
            File f = new File(file.getName());
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(results);
            fos.close();
            Map options = Cloudinary.asMap(
                "transformation",
                new Transformation().width(200).height(200).crop("scale")
            );
            Map uploadResult = cloudinary.uploader().upload(f, options);
            imagePub = (String) uploadResult.get("secure_url");
            f.delete();
        } catch (IOException ex) {
            Logger.getLogger(CompteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deconnexion(){
       getHttpSession().invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AccueilManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void versProfil(String idAmi) {
        getHttpSession().setAttribute("idAmi", idAmi);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("profilami.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AccueilManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprAmi(String idAmi) {
        amisService.supprimeAmi(idAmi, (String)getHttpSession().getAttribute("id"));
    }
    
    private HttpSession getHttpSession() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
        return request.getSession(true);
    }
    
}
