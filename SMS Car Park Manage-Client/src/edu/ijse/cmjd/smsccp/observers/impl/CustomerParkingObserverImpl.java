package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.CustomerParking;
import edu.ijse.cmjd.smsccp.observer.CustomerParkingObserver;
import edu.ijse.cmjd.smsccp.view.DataView;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerParkingObserverImpl extends UnicastRemoteObject implements CustomerParkingObserver {

    private DataView dataView;
    
    public CustomerParkingObserverImpl(DataView dataView) throws RemoteException {
        this.dataView = dataView;
    }
    
    @Override
    public void sentMseeage(String message) throws RemoteException {
        dataView.setMessageForCustomerParking(message);
    }

    @Override
    public void sentCustomerParking(CustomerParking customerParking) throws RemoteException {
        dataView.setnewCustomerParking(customerParking);
    }
    
}
