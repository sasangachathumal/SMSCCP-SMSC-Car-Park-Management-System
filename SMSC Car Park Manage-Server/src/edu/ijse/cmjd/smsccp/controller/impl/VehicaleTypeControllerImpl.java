package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.VehicleTypeController;
import edu.ijse.cmjd.smsccp.fileaccess.VehicletypeFileAccess;
import edu.ijse.cmjd.smsccp.model.VehicleType;
import edu.ijse.cmjd.smsccp.observer.VehicletypeObserver;
import edu.ijse.cmjd.smsccp.observerble.VehicletypeObserverble;
import edu.ijse.cmjd.smsccp.reserve.VehicaleTypeReserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicaleTypeControllerImpl extends UnicastRemoteObject implements VehicleTypeController {

    private final VehicletypeFileAccess access = new VehicletypeFileAccess();
    private static final VehicletypeObserverble OBSERVERBLE = new VehicletypeObserverble();
    private static final VehicaleTypeReserver RESERVER = new VehicaleTypeReserver();
    
    public VehicaleTypeControllerImpl() throws RemoteException {
        
    }

    @Override
    public boolean addVehicleType(VehicleType vehicleType) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddVehicaleType = access.addVehicaleType(vehicleType);
        if (isaddVehicaleType) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setMessage(new String("New Vehicale Type Add !!!"));
                        OBSERVERBLE.setVehicleType(vehicleType);
                    } catch (RemoteException ex) {
                        Logger.getLogger(VehicaleTypeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        return isaddVehicaleType;
    }

    @Override
    public boolean updateVehicleType(VehicleType vehicleType) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveVehicaleTpe(vehicleType.getVehicleType(), this)) {
                boolean isupdateVehicaleType = access.updateVehicaleType(vehicleType);
                if (isupdateVehicaleType) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setMessage(new String("A Vehicale Type Update !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(VehicaleTypeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isupdateVehicaleType;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseVehicaleType(vehicleType.getVehicleType(), this);
        }
    }

    @Override
    public boolean deleteVehicleType(String nic) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveVehicaleTpe(nic, this)) {
                boolean isdeleteVehicaleType = access.deleteVehicaleType(nic);
                if (isdeleteVehicaleType) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setMessage(new String("A Vehicale Type Delete !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(VehicaleTypeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeleteVehicaleType;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseVehicaleType(nic, this);
        }
    }

    @Override
    public VehicleType searchVehicleType(String nic) throws RemoteException, ClassNotFoundException, IOException {
        return access.searchVehicaleType(nic);
    }

    @Override
    public ArrayList<VehicleType> getAllVehicleType() throws RemoteException, ClassNotFoundException, IOException {
        return access.getAllVehicaleTypes();
    }

    @Override
    public boolean reserveVehicaleType(String id) throws RemoteException, IOException {
        return RESERVER.reserveVehicaleTpe(id, this);
    }

    @Override
    public boolean releaseVehicaleType(String id) throws RemoteException, IOException {
        return RESERVER.releaseVehicaleType(id, this);
    }

    @Override
    public void addVehicleTypeObserver(VehicletypeObserver vehicletypeObserver) throws RemoteException, IOException {
        OBSERVERBLE.addVehicleTypeObserver(vehicletypeObserver);
    }

    @Override
    public void removeVehicleTypeObserver(VehicletypeObserver vehicletypeObserver) throws RemoteException, IOException {
        OBSERVERBLE.removeVehicleTypeObserver(vehicletypeObserver);
    }
    
}
