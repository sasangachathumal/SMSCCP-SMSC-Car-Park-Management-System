package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.CustomerParking;
import edu.ijse.cmjd.smsccp.observer.CustomerParkingObserver;
import java.rmi.RemoteException;
import java.util.*;

public class CustomerParkingObserverble {
    
    private CustomerParking customerParking;
    
    private String message;
    
    private static final ArrayList<CustomerParkingObserver> OBSERVERS = new ArrayList<>();
    
    public void setCustomerParkingObserver(CustomerParkingObserver customerParkingObserver) {
        OBSERVERS.add(customerParkingObserver);
    }
    
    public void removeCustomerParkingObserver(CustomerParkingObserver customerParkingObserver) {
        OBSERVERS.remove(customerParkingObserver);
    }
    
    public void notifyCustomerParkingMessages() throws RemoteException {
        for (CustomerParkingObserver customerparkingObserver : OBSERVERS) {
            customerparkingObserver.sentMseeage(this.message);
        }
    }

    public void notifyCustomerParkings() throws RemoteException {
        for (CustomerParkingObserver customerparkingObserver : OBSERVERS) {
            customerparkingObserver.sentCustomerParking(this.customerParking);
        }
    }
    
    public void setCustomerParking(CustomerParking customerParking) throws RemoteException {
        if(this.customerParking != customerParking){
            this.customerParking = customerParking;
            notifyCustomerParkings();
        }
    }
    
    public void setCustomerParkingMessages(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifyCustomerParkingMessages();
        }
    }
    
}
