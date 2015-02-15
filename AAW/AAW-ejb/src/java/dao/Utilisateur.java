/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author sfabien
 */
@Entity
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUtilisateur;
    
    @Column
    private String nom;
    
    @Column
    private String prenom;
    
    @Column
    private String email;
    
    @Column
    private String mdp;

    @OneToMany(mappedBy= "idUtilisateur", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Utilisateur> amis;
    
    @OneToMany(mappedBy= "idUtilisateur", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Utilisateur> demandeAmis;
    
    @OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> messagePublic;
    
    @OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> messagePersonel;
    
    @OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> filActu;
    
    public Utilisateur() {
    }
    
    public Utilisateur(String email,String mdp,String nom) {
        this.nom=nom;
        this.email=email;
        this.mdp=mdp;
        this.amis=new ArrayList<Utilisateur>();
        this.messagePublic=new ArrayList<Message>();
        this.messagePersonel=new ArrayList<Message>();
        this.filActu=new ArrayList<Message>();
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public List<Utilisateur> getAmis() {
        return amis;
    }

    public void setAmis(List<Utilisateur> amis) {
        this.amis = amis;
    }

    public List<Message> getMessagePublic() {
        return messagePublic;
    }

    public void setMessagePublic(List<Message> messagePublic) {
        this.messagePublic = messagePublic;
    }

    public List<Message> getMessagePersonel() {
        return messagePersonel;
    }

    public void setMessagePersonel(List<Message> messagePersonel) {
        this.messagePersonel = messagePersonel;
    }

    public List<Message> getFilActu() {
        return filActu;
    }

    public void setFilActu(List<Message> filActu) {
        this.filActu = filActu;
    }

    public List<Utilisateur> getDemandeAmis() {
        return demandeAmis;
    }

    public void setDemandeAmis(List<Utilisateur> demandeAmis) {
        this.demandeAmis = demandeAmis;
    }
    
}
