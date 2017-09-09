package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.Customer;
import edu.ijse.cmjd.smsccp.observer.CustomerObserver;
import java.io.*;
import java.rmi.*;
import java.util.*;

public interface CustomerController extends Remote {
    
    public boolean addCustomer(Customer customer)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updateCustomer(Customer customer)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deleteCustomer(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public Customer searchCustomer(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<Customer> getAllCustomer()throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reserveCustomer(String nic) throws RemoteException, 
            IOException;
    
    public boolean releaseCustomer(String nic) throws RemoteException, 
            IOException;
    
    public void addCustomerObserver(CustomerObserver customerObserver)throws 
            RemoteException, IOException;
    
    public void removeCustomerObserver(CustomerObserver customerObserver)throws 
            RemoteException, IOException;
    
}
