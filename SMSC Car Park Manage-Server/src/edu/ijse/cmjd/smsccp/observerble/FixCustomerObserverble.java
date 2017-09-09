package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.FixCustomer;
import edu.ijse.cmjd.smsccp.observer.FixCustomerObserver;
import java.rmi.*;
import java.util.*;

public class FixCustomerObserverble {
    
    private String message;
    
    private FixCustomer fixCustomer;
    
    private static ArrayList<FixCustomerObserver> observerlist = new ArrayList<>();
    
    public void setFixCustomerObserver(FixCustomerObserver fixCustomerObserver) {
        observerlist.add(fixCustomerObserver);
    }
    
    public void removeFixCustomerObserver(FixCustomerObserver fixCustomerObserver) {
        observerlist.remove(fixCustomerObserver);
    }
    
    public void notifyFixCustomerMessages() throws RemoteException {
        for (FixCustomerObserver customerparkingObserver : observerlist) {
            customerparkingObserver.sentMessage(message);
        }
    }

    public void notifyFixCustomers() throws RemoteException {
        for (FixCustomerObserver customerparkingObserver : observerlist) {
            customerparkingObserver.sentFixCustomer(fixCustomer);
        }
    }
    
    public void setFixCustomer(FixCustomer fixCustomer) throws RemoteException {
        if(this.fixCustomer != fixCustomer){
            this.fixCustomer = fixCustomer;
            notifyFixCustomers();
        }
    }
    
    public void setFixCustomerMessages(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifyFixCustomerMessages();
        }
    }
}
