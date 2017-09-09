package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.FixCustomer;
import edu.ijse.cmjd.smsccp.observer.FixCustomerObserver;
import edu.ijse.cmjd.smsccp.view.InputCustomers;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FixCustomerObserverImpl extends UnicastRemoteObject implements FixCustomerObserver {

    private InputCustomers inputCustomers;
    
    public FixCustomerObserverImpl(InputCustomers inputCustomers) throws RemoteException {
        this.inputCustomers = inputCustomers;
    }
    
    @Override
    public void sentMessage(String message) throws RemoteException {
        inputCustomers.loadCustomers();
    }

    @Override
    public void sentFixCustomer(FixCustomer fixCustomer) throws RemoteException {
        inputCustomers.newInputFixCustomer(fixCustomer);
    }
    
}
