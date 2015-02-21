/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Local;

/**
 *
 * @author kk
 */
@Local
public interface UtilisateurSessionBeanLocal {
    public void save(Utilisateur i) ;
    public void update(Utilisateur i);
    public void delete(Utilisateur i);
    public Utilisateur getUtilisateur(String id);
}
