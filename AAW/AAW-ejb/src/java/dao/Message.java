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
}
