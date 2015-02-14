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
    
    @OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> messagePublic;
    
    @OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> messagePersonel;
    
    @OneToMany(mappedBy= "idMessage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> filActu;
    
    public Utilisateur() {
    }
    
}
