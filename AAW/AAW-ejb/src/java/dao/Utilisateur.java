package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String email;
    
    @Column
    private String nom;
    
    @Column
    private String prenom;
    
    @Column
    private String mdp;
    
    @Column
    private String image;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Notifications> notifications = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
	name="USER_AMIS",
	joinColumns=@JoinColumn(name="ref_user"),
	inverseJoinColumns=@JoinColumn(name="ref_ami")
    )
    private List<Utilisateur> amis = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
	name="USER_DEMANDEAMIS",
	joinColumns=@JoinColumn(name="ref_user"),
	inverseJoinColumns=@JoinColumn(name="ref_ami")
    )
    private List<Utilisateur> demandeAmis = new ArrayList<>();
    
    //@OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    //private List<Message> messagePublic;
    
    //@OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    //private List<Message> filActu;

    public Utilisateur() {
    }   
    
    public Utilisateur(String email,String mdp,String nom, String prenom) {
        this.email=email;
        this.mdp=mdp;
        this.nom=nom;
        this.prenom=prenom;
        this.image = "http://res.cloudinary.com/df6myek6a/image/upload/v1418980152/default_avatar.png";
    }

    public void addDemandeAmi(Utilisateur u) {
        this.demandeAmis.add(u);
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications.add(notifications);
    }
    
    public void suppDemandeAmi(Utilisateur u) {
        this.demandeAmis.remove(u);
    }
    
    public List<Utilisateur> getDemandeAmis() {
        return demandeAmis;
    }

    public void setDemandeAmis(List<Utilisateur> demandeAmis) {
        this.demandeAmis = demandeAmis;
    }

    public void addAmi(Utilisateur u) {
        this.amis.add(u);
    }
    
    public List<Utilisateur> getAmis() {
        return amis;
    }

    public void setAmis(List<Utilisateur> amis) {
        this.amis = amis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    
    public void addMessage(Message m) {
        this.messages.add(m);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}