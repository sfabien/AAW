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
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMessage;
    
    @Column
    private Utilisateur emetteur;
    
    @Column
    private Utilisateur recepteur;
    
    @Column
    private String message;
    
    public Message() {
    }
    
    public Message(Utilisateur em, Utilisateur re, String message) {
        this.emetteur=em;
        this.recepteur=re;
        this.message=message;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Utilisateur getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Utilisateur emetteur) {
        this.emetteur = emetteur;
    }

    public Utilisateur getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(Utilisateur recepteur) {
        this.recepteur = recepteur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
