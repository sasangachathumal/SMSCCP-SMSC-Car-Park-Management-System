package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.FixCustomer;
import java.rmi.*;

public interface FixCustomerObserver extends Remote {
    
    public void sentMessage(String message) throws RemoteException;
    
    public void sentFixCustomer(FixCustomer fixCustomer) throws RemoteException;
    
}
