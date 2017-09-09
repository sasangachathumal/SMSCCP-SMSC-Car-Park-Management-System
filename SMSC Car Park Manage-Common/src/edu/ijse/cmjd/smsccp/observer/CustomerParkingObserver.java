package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.CustomerParking;
import java.rmi.*;

public interface CustomerParkingObserver extends Remote {
    
    public void sentMseeage(String message) throws RemoteException;
    
    public void sentCustomerParking(CustomerParking customerParking) throws 
            RemoteException;
    
}
