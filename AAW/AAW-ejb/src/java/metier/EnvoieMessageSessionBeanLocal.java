/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.Message;
import dao.Utilisateur;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author kk
 */
@Local
public interface EnvoieMessageSessionBeanLocal {
    public void envoieMessagePublic(String message, Utilisateur emetteur, Utilisateur recepteur, String url);
    public void envoieMessagePrive(Message h);
    public ArrayList<Message> mur(Utilisateur u);
}
