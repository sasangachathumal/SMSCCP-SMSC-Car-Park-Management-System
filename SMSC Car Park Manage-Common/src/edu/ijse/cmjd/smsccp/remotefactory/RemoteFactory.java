package edu.ijse.cmjd.smsccp.remotefactory;

import edu.ijse.cmjd.smsccp.controller.SectionController;
import edu.ijse.cmjd.smsccp.controller.CustomerParkingController;
import edu.ijse.cmjd.smsccp.controller.JobroleController;
import edu.ijse.cmjd.smsccp.controller.VehicleTypeController;
import edu.ijse.cmjd.smsccp.controller.CustomerController;
import edu.ijse.cmjd.smsccp.controller.UserController;
import edu.ijse.cmjd.smsccp.controller.PlaceController;
import edu.ijse.cmjd.smsccp.controller.FixCustomerController;
import java.rmi.*;

public interface RemoteFactory extends Remote {
    
    public CustomerController getCustomerController() throws RemoteException;
    
    public CustomerParkingController getCustomerParkingController() throws 
            RemoteException;
    
    public FixCustomerController getFixCustomerController() throws RemoteException;
    
    public PlaceController getPlaceController() throws RemoteException;
    
    public UserController getUserController() throws RemoteException;
    
    public JobroleController getJobroleController() throws RemoteException;
    
    public SectionController getSectionController() throws RemoteException;
    
    public VehicleTypeController getVehicleTypeController() throws RemoteException;
    
}
