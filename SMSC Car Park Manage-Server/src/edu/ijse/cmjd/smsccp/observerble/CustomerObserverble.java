package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.Customer;
import edu.ijse.cmjd.smsccp.observer.CustomerObserver;
import java.rmi.RemoteException;
import java.util.*;

public class CustomerObserverble {
    
    private String message;
    
    private Customer customer;
    
    private static ArrayList<CustomerObserver> observerList = new ArrayList<>();
    
    public void addCustomerObserver(CustomerObserver customerObserver) {
        observerList.add(customerObserver);
    }
    
    public void removeCustomerObserver(CustomerObserver customerObserver) {
        observerList.remove(customerObserver);
    }
    
    public void notifyCustomerMessages() throws RemoteException {
        for (CustomerObserver customerObserver : observerList) {
            customerObserver.sentMessages(message);
        }
    }
    
    public void notifyCustomers() throws RemoteException {
        for (CustomerObserver customerObserver : observerList) {
            customerObserver.sentCustomer(customer);
        }
    }
    
    public void setMessage(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifyCustomerMessages();
        }
    }
    
    public void setCustomers(Customer customer) throws RemoteException {
        if(this.customer != customer){
            this.customer = customer;
            notifyCustomers();
        }
    }
    
}
