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
    private List<Message> messages = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
	name="USER_AMIS",
	joinColumns=@JoinColumn(name="ref_user"),
	inverseJoinColumns=@JoinColumn(name="ref_ami")
    )
    private List<Utilisateur> amis;
    
    //@OneToMany(mappedBy= "idUtilisateur", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    //private List<Utilisateur> demandeAmis;
    
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