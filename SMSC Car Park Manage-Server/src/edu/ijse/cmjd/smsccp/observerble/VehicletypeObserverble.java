package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.VehicleType;
import edu.ijse.cmjd.smsccp.observer.VehicletypeObserver;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class VehicletypeObserverble {

    private String message;
    
    private VehicleType vehicleType;
    
    private static ArrayList<VehicletypeObserver> observerList = new ArrayList<>();
    
    public void addVehicleTypeObserver(VehicletypeObserver vehicleTypeObserver) {
        observerList.add(vehicleTypeObserver);
    }
    
    public void removeVehicleTypeObserver(VehicletypeObserver vehicleTypeObserver) {
        observerList.remove(vehicleTypeObserver);
    }
    
    public void notifyVehicleTypeMessages() throws RemoteException {
        for (VehicletypeObserver vehicleTypeObserver : observerList) {
            vehicleTypeObserver.sentMessage(message);
        }
    }
    
    public void notifyVehicleType() throws RemoteException {
        for (VehicletypeObserver vehicleTypeObserver : observerList) {
            vehicleTypeObserver.sentVehicletype(vehicleType);
        }
    }
    
    public void setMessage(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifyVehicleTypeMessages();
        }
    }
    
    public void setVehicleType(VehicleType vehicleType) throws RemoteException {
        if(this.vehicleType != vehicleType){
            this.vehicleType = vehicleType;
            notifyVehicleType();
        }
    }
    
}
