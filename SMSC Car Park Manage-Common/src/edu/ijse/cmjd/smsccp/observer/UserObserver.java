package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.Users;
import java.rmi.*;

public interface UserObserver extends Remote {
    
    public void sentMessage(String message) throws RemoteException;
    
    public void sentUser(Users users) throws RemoteException;
    
}
