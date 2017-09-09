package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.CustomerParking;
import edu.ijse.cmjd.smsccp.observer.CustomerParkingObserver;
import java.io.*;
import java.rmi.*;
import java.util.*;

public interface CustomerParkingController extends Remote {
    
    public boolean addCustomerParking(CustomerParking customerParking)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updateCustomerParking(CustomerParking customerParking)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deleteCustomerParking(String placeId, String customerId, String date)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<CustomerParking> searchCustomerParking(String customerId, String date)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<CustomerParking> getAllCustomerParking()throws RemoteException, 
            ClassNotFoundException, IOException;

    public ArrayList<CustomerParking> getAllCustomerDalyParking(String date)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reserveCustomerParking(String nic) throws RemoteException, 
            IOException;
    
    public boolean releaseCustomerParking(String nic) throws RemoteException, 
            IOException;
    
    public void addCustomerParkingObserver(CustomerParkingObserver customerParkingObserver)throws 
            RemoteException, IOException;
    
    public void removeCustomerParkingObserver(CustomerParkingObserver customerParkingObserver)throws 
            RemoteException, IOException;
    
}
