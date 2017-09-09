package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.CustomerParkingController;
import edu.ijse.cmjd.smsccp.fileaccess.CustomerParkingFileAccess;
import edu.ijse.cmjd.smsccp.model.CustomerParking;
import edu.ijse.cmjd.smsccp.observer.CustomerParkingObserver;
import edu.ijse.cmjd.smsccp.observerble.CustomerParkingObserverble;
import edu.ijse.cmjd.smsccp.reserve.CustomerParkingReserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerParkingControllerImpl extends UnicastRemoteObject implements CustomerParkingController{

    private final CustomerParkingFileAccess fileAccess = new CustomerParkingFileAccess();
    private static final CustomerParkingObserverble OBSERVERBLE = new CustomerParkingObserverble();
    private static final CustomerParkingReserver RESERVER = new CustomerParkingReserver();
    
    public CustomerParkingControllerImpl() throws RemoteException {
        
    }
    
    @Override
    public boolean addCustomerParking(CustomerParking customerParking) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddCustomerParking = fileAccess.addCustomerParking(customerParking);
        if (isaddCustomerParking) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setCustomerParkingMessages(new String("New Customer Parking Added !!!"));
                        OBSERVERBLE.setCustomerParking(customerParking);
                    } catch (RemoteException ex) {
                        Logger.getLogger(CustomerParkingControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        return isaddCustomerParking;
    }

    @Override
    public boolean updateCustomerParking(CustomerParking customerParking) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveCustomerParking(customerParking.getCustomerNIC(), this)) {
                boolean isupdateCustomerParking = fileAccess.updateCustomerParking(customerParking);
                if (isupdateCustomerParking) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setCustomerParkingMessages(new String("A Customer Parking Update !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(CustomerParkingControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isupdateCustomerParking;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseCustomerParking(customerParking.getCustomerNIC(), this);
        }
    }

    @Override
    public boolean deleteCustomerParking(String placeId, String customerId, String date) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveCustomerParking(customerId, this)) {
                boolean isdeleteCustomerParking = fileAccess.deleteCustomerParking(placeId,customerId,date);
                if (isdeleteCustomerParking) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setCustomerParkingMessages(new String("A Customer Parking Delete !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(CustomerParkingControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeleteCustomerParking;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseCustomerParking(customerId, this);
        }
    }

    @Override
    public ArrayList<CustomerParking> searchCustomerParking(String customerId, String date) throws RemoteException, ClassNotFoundException, IOException {
        return fileAccess.searchCustomerParking(customerId ,date);
    }

    @Override
    public ArrayList<CustomerParking> getAllCustomerParking() throws RemoteException, ClassNotFoundException, IOException {
        return fileAccess.getAllCustomerParking();
    }

    @Override
    public ArrayList<CustomerParking> getAllCustomerDalyParking(String date) throws RemoteException, ClassNotFoundException, IOException {
        return fileAccess.getDalyCustomerParking(date);
    }

    @Override
    public boolean reserveCustomerParking(String nic) throws RemoteException, IOException {
        return RESERVER.reserveCustomerParking(nic, this);
    }

    @Override
    public boolean releaseCustomerParking(String nic) throws RemoteException, IOException {
        return RESERVER.releaseCustomerParking(nic, this);
    }

    @Override
    public void addCustomerParkingObserver(CustomerParkingObserver customerParkingObserver) throws RemoteException, IOException {
        OBSERVERBLE.setCustomerParkingObserver(customerParkingObserver);
    }

    @Override
    public void removeCustomerParkingObserver(CustomerParkingObserver customerParkingObserver) throws RemoteException, IOException {
        OBSERVERBLE.removeCustomerParkingObserver(customerParkingObserver);
    }
    
}
