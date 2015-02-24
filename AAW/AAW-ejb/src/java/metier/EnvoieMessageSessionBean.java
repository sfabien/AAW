/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Message;
import dao.MessageSessionBeanLocal;
import dao.Utilisateur;
import dao.UtilisateurSessionBeanLocal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kk
 */
@Stateless
public class EnvoieMessageSessionBean implements EnvoieMessageSessionBeanLocal {

    @EJB
    MessageSessionBeanLocal messageDao;

    @EJB
    UtilisateurSessionBeanLocal utilisateurDao;

    @Override
    public void envoieMessagePublic(String message, Utilisateur emetteur, Utilisateur recepteur) {
        Message m = new Message(message, emetteur, recepteur, Message.PUBLIC);
        Message mess = messageDao.save(m);
        if (recepteur.getEmail().equals(emetteur.getEmail())) {
            recepteur.addMessage(mess);
            utilisateurDao.update(recepteur);
        } else {
            recepteur.addMessage(mess);
            utilisateurDao.update(recepteur);
            emetteur.addMessage(mess);
            utilisateurDao.update(emetteur);
        }
    }

    @Override
    public void envoieMessagePrive(Message h) {
        //messageDao.save(h);
        /*Utilisateur emetteur=h.getEmetteur();
         Utilisateur destinataire = h.getRecepteur();
         destinataire.getMessagePersonel().add(h);
         emetteur.getMessagePersonel().add(h);*/
        //em.flush();
    }

    private boolean contientMessage(ArrayList<Message> mess, Message m) {
        for (Message me : mess) {
            if (me.getId().equals(m.getId()) && me.getDateEnvoi().equals(m.getDateEnvoi())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Message> mur(Utilisateur u) {
        ArrayList<Message> mess = new ArrayList<>();
        for (Message m : messageDao.listByUser(u)) {
            if (m.getDiscriminant().equals(Message.PUBLIC)) {
                if (!contientMessage(mess, m)) {
                    mess.add(m);
                }
            }
        }
        for (Utilisateur ami : u.getAmis()) {
            for (Message m : ami.getMessages()) {
                if (m.getDiscriminant().equals(Message.PUBLIC)) {
                    if (!contientMessage(mess, m)) {
                        mess.add(m);
                    }
                }
            }
        }
        Collections.sort(mess, new Comparator<Message>() {
            @Override
            public int compare(Message s1, Message s2) {
                return -s1.getDateEnvoi().compareTo(s2.getDateEnvoi());
            }
        });
        return mess;
        /*Query q = em.createQuery(
         "SELECT h FROM Utilisateur h WHERE h.idUtilisateur =?");
         q.setParameter(1,idpersonne);*/

        /*if(!q.getResultList().isEmpty()){
         Utilisateur u = (Utilisateur) q.getResultList().get(0);*/
        /*if(idCourant.equals(idpersonne)){
         List<Message> m = u.getMessagePersonel();
         for(Message me:u.getMessagePublic()){
         m.add(me);
         }
         return m;
         }else{
         return u.getMessagePublic();
         }*/
        //}
    }
}
