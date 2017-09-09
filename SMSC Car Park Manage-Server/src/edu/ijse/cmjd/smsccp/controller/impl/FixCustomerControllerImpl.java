package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.FixCustomerController;
import edu.ijse.cmjd.smsccp.fileaccess.FixCustomerFileAccess;
import edu.ijse.cmjd.smsccp.model.FixCustomer;
import edu.ijse.cmjd.smsccp.observer.FixCustomerObserver;
import edu.ijse.cmjd.smsccp.observerble.FixCustomerObserverble;
import edu.ijse.cmjd.smsccp.reserve.FixCustomerReserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FixCustomerControllerImpl extends UnicastRemoteObject implements FixCustomerController{

    private final FixCustomerFileAccess access = new FixCustomerFileAccess();
    private static final FixCustomerObserverble OBSERVERBLE = new FixCustomerObserverble();
    private static final FixCustomerReserver RESERVER = new FixCustomerReserver();
    
    public FixCustomerControllerImpl() throws RemoteException{
        
    }
    
    @Override
    public boolean addFixCustomer(FixCustomer fixcustomer) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddFixCustomer = access.addFixCustomer(fixcustomer);
        if (isaddFixCustomer) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setFixCustomerMessages(new String("New FixCustomer Added !!!"));
                        OBSERVERBLE.setFixCustomer(fixcustomer);
                    } catch (RemoteException ex) {
                        Logger.getLogger(FixCustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        return isaddFixCustomer;
    }

    @Override
    public boolean updateFixCustomer(FixCustomer fixcustomer) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveFixCustomer(fixcustomer.getFxCustomerINC(), this)) {
                boolean isupdateFixCustomer = access.updateFixCustomer(fixcustomer);
                if (isupdateFixCustomer) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setFixCustomerMessages(new String("A FixCustomer Update !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(FixCustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isupdateFixCustomer;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseFixCustomer(fixcustomer.getFxCustomerINC(), this);
        }
    }

    @Override
    public boolean deleteFixCustomer(String nic) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveFixCustomer(nic, this)) {
                boolean isdeleteFixCustomer = access.deleteFixCustomer(nic);
                if (isdeleteFixCustomer) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setFixCustomerMessages(new String("A FixCustomer Delete !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(FixCustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeleteFixCustomer;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseFixCustomer(nic, this);
        }
    }

    @Override
    public FixCustomer searchFixCustomer(String nic) throws RemoteException, ClassNotFoundException, IOException {
        return access.searchFixCustomer(nic);
    }

    @Override
    public ArrayList<FixCustomer> getAllFixCustomer() throws RemoteException, ClassNotFoundException, IOException {
        return access.getAllFixCustomer();
    }

    @Override
    public boolean reserveFixCustomer(String nic) throws RemoteException, IOException {
        return RESERVER.reserveFixCustomer(nic, this);
    }

    @Override
    public boolean releaseFixCustomer(String nic) throws RemoteException, IOException {
        return RESERVER.releaseFixCustomer(nic, this);
    }

    @Override
    public void addFixCustomerObserver(FixCustomerObserver fixCustomerObserver) throws RemoteException, IOException {
        OBSERVERBLE.setFixCustomerObserver(fixCustomerObserver);
    }

    @Override
    public void removeFixCustomerObserver(FixCustomerObserver fixCustomerObserver) throws RemoteException, IOException {
        OBSERVERBLE.removeFixCustomerObserver(fixCustomerObserver);
    }
    
}
