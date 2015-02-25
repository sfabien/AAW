/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Message;
import dao.MessageSessionBeanLocal;
import dao.NotificationsSessionBeanLocal;
import dao.Notifications;
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
    NotificationsSessionBeanLocal notificationsDao;

    @EJB
    UtilisateurSessionBeanLocal utilisateurDao;

    @Override
    public void envoieMessagePublic(String message, Utilisateur emetteur, Utilisateur recepteur) {
        Message m = new Message(message, emetteur, recepteur, Message.PUBLIC);
        
        
        String delims = "[ \n]+";
        String[] tokens = message.split(delims);
        for (int i = 0; i < tokens.length; i++){
            if(tokens[i].charAt(0)=='{' && tokens[i].charAt(tokens[i].length()-1)=='}'){
                String delims2 = "[.]+";
                String[] tokens2 = tokens[i].split(delims2);
                if(tokens2.length==2){
                    String nom = tokens2[0];
                    nom=nom.substring(1, nom.length());
                    String prenom = tokens2[1];
                    prenom=prenom.substring(0, prenom.length()-1);
                    System.out.println(" la  : "+nom + " " +prenom);
                    for(Utilisateur u : emetteur.getAmis()){
                        if(u.getNom().equals(nom) && u.getPrenom().equals(prenom)){
                            Notifications n = new Notifications();
                            n.setNotifications("Vous avez été notifié dans"
                                    + " une publication par " + emetteur.getNom()+
                                    " " + emetteur.getPrenom() + ".");
                            notificationsDao.save(n);
                            u.setNotifications(n);
                            utilisateurDao.update(u);
                        }
                    }
                }
            }
            if(tokens[i].length()>32){
                if(tokens[i].substring(0, 32).equals("https://www.youtube.com/watch?v=")){
                    String id=tokens[i].substring(32,tokens[i].length());
                    m.setUrl(id);
                    m.setDiscriminant(2);
                    System.out.println("ici" + id);
                }
            }
        }
        
        Message mess = messageDao.save(m);
        System.out.println("ici");
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
            
                if (!contientMessage(mess, m)) {
                    mess.add(m);
                
            }
        }
        for (Utilisateur ami : u.getAmis()) {
            for (Message m : ami.getMessages()) {
                    if (!contientMessage(mess, m)) {
                        mess.add(m);
                    
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
