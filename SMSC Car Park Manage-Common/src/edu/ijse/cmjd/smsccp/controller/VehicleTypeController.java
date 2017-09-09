package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.VehicleType;
import edu.ijse.cmjd.smsccp.observer.VehicletypeObserver;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface VehicleTypeController extends Remote{
       public boolean addVehicleType(VehicleType vehicleType)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updateVehicleType(VehicleType vehicleType)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deleteVehicleType(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public VehicleType searchVehicleType(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<VehicleType> getAllVehicleType()throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reserveVehicaleType(String id) throws RemoteException, IOException;
    
    public boolean releaseVehicaleType(String id) throws RemoteException, IOException;
    
    public void addVehicleTypeObserver(VehicletypeObserver vehicletypeObserver)throws 
            RemoteException, IOException;
    
    public void removeVehicleTypeObserver(VehicletypeObserver vehicletypeObserver)throws 
            RemoteException, IOException;
}
