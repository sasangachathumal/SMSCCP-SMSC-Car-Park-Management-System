package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.FixCustomer;
import edu.ijse.cmjd.smsccp.observer.FixCustomerObserver;
import java.io.*;
import java.rmi.*;
import java.util.*;

public interface FixCustomerController extends Remote{
    public boolean addFixCustomer(FixCustomer fixcustomer)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updateFixCustomer(FixCustomer fixcustomer)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deleteFixCustomer(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public FixCustomer searchFixCustomer(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<FixCustomer> getAllFixCustomer()throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reserveFixCustomer(String nic) throws RemoteException, 
            IOException;
    
    public boolean releaseFixCustomer(String nic) throws RemoteException, 
            IOException;
    
    public void addFixCustomerObserver(FixCustomerObserver fixCustomerObserver)
            throws RemoteException, IOException;
    
    public void removeFixCustomerObserver(FixCustomerObserver fixCustomerObserver)
            throws RemoteException, IOException;
}
