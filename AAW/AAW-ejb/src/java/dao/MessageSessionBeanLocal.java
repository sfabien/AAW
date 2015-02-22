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
public interface MessageSessionBeanLocal {
    public Message save(Message i);
    public void update(Message i);
    public void delete(Message i);
}
