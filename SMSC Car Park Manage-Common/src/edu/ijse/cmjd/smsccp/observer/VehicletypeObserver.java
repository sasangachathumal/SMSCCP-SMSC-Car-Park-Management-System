package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.VehicleType;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VehicletypeObserver extends Remote{
    public void sentMessage(String message) throws RemoteException;
    public void sentVehicletype(VehicleType vehicleType) throws RemoteException;
}
