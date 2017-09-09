package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.VehicleType;
import edu.ijse.cmjd.smsccp.observer.VehicletypeObserver;
import edu.ijse.cmjd.smsccp.view.InputPlace;
import edu.ijse.cmjd.smsccp.view.inputVehicaleType;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VehicleTypeObserverImpl extends UnicastRemoteObject implements VehicletypeObserver {

    private inputVehicaleType vehicaleType;
    private InputPlace inputPlace; 
    
    public VehicleTypeObserverImpl(inputVehicaleType vehicaleType) throws RemoteException {
        this.vehicaleType = vehicaleType;
    }
    
    public VehicleTypeObserverImpl(InputPlace inputPlace) throws RemoteException {
        this.inputPlace = inputPlace;
    }
    
    @Override
    public void sentMessage(String message) throws RemoteException {
        inputPlace.loadVehicleTypes();
        vehicaleType.setMessageForNewVehicleType(message);
    }

    @Override
    public void sentVehicletype(VehicleType vehicleType) throws RemoteException {
        
    }
    
}
