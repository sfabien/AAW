package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final Integer PUBLIC = 1;
    public static final Integer PRIVEE = 2;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Utilisateur emetteur;
    
    @ManyToOne
    private Utilisateur recepteur;
    
    @Column
    private String message;
    
    @Column
    private String url;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoi;

    @Column
    private Integer discriminantMedia;
    
    @Column
    private Integer discriminant;
    
    public Message() {
    }
    
    public Message(String message, Utilisateur em, Utilisateur re, int discrimant) {
        this.emetteur=em;
        this.recepteur=re;
        this.message=message;
        this.discriminant=discrimant;
    }

    public Integer getDiscriminantMedia() {
        return discriminantMedia;
    }

    public void setDiscriminantMedia(Integer discriminantMedia) {
        this.discriminantMedia = discriminantMedia;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
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
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDiscriminant() {
        return discriminant;
    }

    public void setDiscriminant(Integer discriminant) {
        this.discriminant = discriminant;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}