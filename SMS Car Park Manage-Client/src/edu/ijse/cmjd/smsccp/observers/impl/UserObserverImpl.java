package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.Users;
import edu.ijse.cmjd.smsccp.observer.UserObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserObserverImpl extends UnicastRemoteObject implements UserObserver {

    
    
    public UserObserverImpl() throws RemoteException {
        
    }
    
    @Override
    public void sentMessage(String message) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sentUser(Users users) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
