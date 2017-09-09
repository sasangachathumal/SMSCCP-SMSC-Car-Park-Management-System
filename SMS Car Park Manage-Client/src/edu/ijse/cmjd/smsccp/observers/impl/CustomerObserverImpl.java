package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.Customer;
import edu.ijse.cmjd.smsccp.observer.CustomerObserver;
import edu.ijse.cmjd.smsccp.view.DataView;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerObserverImpl extends UnicastRemoteObject implements CustomerObserver {

    private DataView dataView;
    
    public CustomerObserverImpl(DataView dataView) throws RemoteException {
        this.dataView = dataView;
    }
    
    @Override
    public void sentMessages(String message) throws RemoteException {
        dataView.setnewMessageforCustomer(message);
    }

    @Override
    public void sentCustomer(Customer customer) throws RemoteException {
        dataView.setnewCustomer(customer);
    }
    
}
