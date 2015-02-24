/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;


import com.cloudinary.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import metier.AuthentificatorSessionBeanLocal;

@ManagedBean(name = "compteManagedBean")
@RequestScoped
public class CompteManagedBean {

    @EJB
    AuthentificatorSessionBeanLocal authService;

    Cloudinary cloudinary = new Cloudinary(Cloudinary.asMap(
            "cloud_name", "df6myek6a",
            "api_key", "137416967793742",
            "api_secret", "ew-lrT6DyApJgVRUlO5yILg7dac"));

    private Part file;

    public CompteManagedBean() {
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
        changeImage();
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
            String url = (String) uploadResult.get("secure_url");
            authService.changeImage((String)getHttpSession().getAttribute("id"), url);
            f.delete();
        } catch (IOException ex) {
            Logger.getLogger(CompteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HttpSession getHttpSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getSession(true);

    }

}
