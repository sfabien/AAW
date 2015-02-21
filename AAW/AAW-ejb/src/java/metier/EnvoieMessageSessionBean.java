/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Message;
import dao.MessageSessionBeanLocal;
import dao.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author kk
 */
@Stateless
public class EnvoieMessageSessionBean implements EnvoieMessageSessionBeanLocal {
    @EJB
    MessageSessionBeanLocal msbl;
    
    @PersistenceContext(unitName="AAW-ejbPU",type=PersistenceContextType.TRANSACTION)
    private EntityManager em; 
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void envoieMessagePublic(Message h) {
        msbl.save(h);
        /*Utilisateur emetteur=h.getEmetteur();
        Utilisateur destinataire = h.getRecepteur();
        destinataire.getMessagePublic().add(h);
        destinataire.getFilActu().add(h);
        emetteur.getMessagePublic().add(h);
        
        for(Utilisateur u : destinataire.getAmis()){
            u.getFilActu().add(h);
        }
        for(Utilisateur u : emetteur.getAmis()){
            //evite les doublons
            if(!destinataire.getAmis().contains(u))
                u.getFilActu().add(h);
        }*/
        
        em.flush();
    }

    @Override
    public void envoieMessagePrive(Message h) {
        msbl.save(h);
        /*Utilisateur emetteur=h.getEmetteur();
        Utilisateur destinataire = h.getRecepteur();
        destinataire.getMessagePersonel().add(h);
        emetteur.getMessagePersonel().add(h);*/
        em.flush();
    }


    @Override
    public List<Message> mur(Integer idCourant, Integer idpersonne) {
        Query q = em.createQuery(
            "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
        q.setParameter(1,idpersonne);
        
        if(!q.getResultList().isEmpty()){
            Utilisateur u = (Utilisateur) q.getResultList().get(0);
            /*if(idCourant.equals(idpersonne)){
                List<Message> m = u.getMessagePersonel();
                for(Message me:u.getMessagePublic()){
                    m.add(me);
                }
                return m;
            }else{
                return u.getMessagePublic();
            }*/
        }
        return null;
    }
}
