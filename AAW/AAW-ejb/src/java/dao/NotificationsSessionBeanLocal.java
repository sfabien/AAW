/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author kk
 */
@Local
public interface NotificationsSessionBeanLocal {
    public Notifications save(Notifications i);
    public void update(Notifications i);
    public void delete(Notifications i);
    public Notifications find(long u);
}
