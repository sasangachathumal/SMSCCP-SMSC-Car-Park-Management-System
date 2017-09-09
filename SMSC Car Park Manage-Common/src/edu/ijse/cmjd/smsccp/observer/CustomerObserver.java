package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.Customer;
import java.rmi.*;

public interface CustomerObserver extends Remote {
    
    public void sentMessages(String message) throws RemoteException;
    
    public void sentCustomer(Customer customer) throws RemoteException;
    
}
