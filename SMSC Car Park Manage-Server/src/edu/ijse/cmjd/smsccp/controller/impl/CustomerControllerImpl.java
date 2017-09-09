package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.CustomerController;
import edu.ijse.cmjd.smsccp.fileaccess.CustomerFileAccess;
import edu.ijse.cmjd.smsccp.model.Customer;
import edu.ijse.cmjd.smsccp.observer.CustomerObserver;
import edu.ijse.cmjd.smsccp.observerble.CustomerObserverble;
import edu.ijse.cmjd.smsccp.reserve.CustomerReserver;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerControllerImpl extends UnicastRemoteObject implements CustomerController {

    private final CustomerFileAccess fileAccess = new CustomerFileAccess();
    private static final CustomerObserverble OBSERVERBLE = new CustomerObserverble();
    private static final CustomerReserver RESERVER = new CustomerReserver();

    public CustomerControllerImpl() throws RemoteException {

    }

    @Override
    public boolean addCustomer(Customer customer) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddCustomer = fileAccess.addCustomer(customer);
        if (isaddCustomer) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setMessage(new String("New Customer Added !!!"));
                        OBSERVERBLE.setCustomers(customer);
                    } catch (RemoteException ex) {
                        Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        return isaddCustomer;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws RemoteException, ClassNotFoundException, IOException {
        return false;
        /*
         try {
         if (customerReserver.reserveCustomer(customer.getId(), this)) {
         boolean isUpdate=dBAccess.updateCustomer(customer);
         if(isUpdate){
         new Thread(){
         public void run(){
         try {
         CUSTOMER_OBSERVERBLE.setMessage(new String("Update a Customer"));
         } catch (RemoteException ex) {
         Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         }.start();
         }
         return isUpdate;
         } else {
         return false;
         }
         } finally {
         customerReserver.releaseCustomer(customer.getId(), this);
         }
         */
    }

    @Override
    public boolean deleteCustomer(String nic) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveCustomer(nic, this)) {
                boolean isdeleteCustomer = fileAccess.deleteCustomer(nic);
                if (isdeleteCustomer) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setMessage(new String("A Customer Deleted !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeleteCustomer;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseCustomer(nic, this);
        }
    }

    @Override
    public Customer searchCustomer(String nic) throws RemoteException, ClassNotFoundException, IOException {
        return fileAccess.searchCustomer(nic);
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws RemoteException, ClassNotFoundException, IOException {
        return fileAccess.getAllCustomer();
    }

    @Override
    public boolean reserveCustomer(String nic) throws RemoteException, IOException {
        return RESERVER.reserveCustomer(nic, this);
    }

    @Override
    public boolean releaseCustomer(String nic) throws RemoteException, IOException {
        return RESERVER.releaseCustomer(nic, this);
    }

    @Override
    public void addCustomerObserver(CustomerObserver customerObserver) throws RemoteException, IOException {
        OBSERVERBLE.addCustomerObserver(customerObserver);
    }

    @Override
    public void removeCustomerObserver(CustomerObserver customerObserver) throws RemoteException, IOException {
        OBSERVERBLE.removeCustomerObserver(customerObserver);
    }

}
