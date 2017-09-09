package edu.ijse.cmjd.smsccp.serverconnector;

import edu.ijse.cmjd.smsccp.controller.CustomerController;
import edu.ijse.cmjd.smsccp.controller.CustomerParkingController;
import edu.ijse.cmjd.smsccp.controller.FixCustomerController;
import edu.ijse.cmjd.smsccp.controller.JobroleController;
import edu.ijse.cmjd.smsccp.controller.PlaceController;
import edu.ijse.cmjd.smsccp.controller.SectionController;
import edu.ijse.cmjd.smsccp.controller.UserController;
import edu.ijse.cmjd.smsccp.controller.VehicleTypeController;
import edu.ijse.cmjd.smsccp.remotefactory.RemoteFactory;
import java.net.*;
import java.rmi.*;

public class ServerConnector {
    
    private static ServerConnector serverConnector;
    private CustomerController customerController;
    private CustomerParkingController customerParkingController;
    private FixCustomerController fixCustomerController;
    private PlaceController placeController;
    private UserController userController;
    private JobroleController jobroleController;
    private SectionController sectionController;
    private VehicleTypeController vehicleTypeController;
    private final RemoteFactory remoteFactory;
    
    private ServerConnector() throws NotBoundException, MalformedURLException, RemoteException{
        remoteFactory=(RemoteFactory)Naming.lookup("rmi://localhost:5050/SMSCCP");
    }
    
    public static ServerConnector getServerConnector() throws NotBoundException, MalformedURLException, RemoteException{
        if(serverConnector==null){
            serverConnector=new ServerConnector();
        }
        return serverConnector;
    }
    public CustomerController getCustomerController() throws RemoteException{
        if(customerController==null){
            customerController=remoteFactory.getCustomerController(); 
        }
        return customerController;
    }
    public CustomerParkingController getCustomerParkingController() throws RemoteException{
        if(customerParkingController==null){
            customerParkingController=remoteFactory.getCustomerParkingController(); 
        }
        return customerParkingController;
    }
    public FixCustomerController getFixCustomerController() throws RemoteException{
        if(fixCustomerController==null){
            fixCustomerController=remoteFactory.getFixCustomerController();
        }
        return fixCustomerController;
    }
    public PlaceController getPlaceController() throws RemoteException{
        if(placeController==null){
            placeController=remoteFactory.getPlaceController();
        }
        return placeController;
    }
    public UserController getUserController() throws RemoteException{
        if(userController==null){
            userController=remoteFactory.getUserController();
        }
        return userController;
    }
    public JobroleController getJobroleController() throws RemoteException{
        if(jobroleController==null){
            jobroleController=remoteFactory.getJobroleController();
        }
        return jobroleController;
    }
    public SectionController getSectionController() throws RemoteException{
        if(sectionController==null){
            sectionController=remoteFactory.getSectionController();
        }
        return sectionController;
    }
    public VehicleTypeController getVehicleTypeController() throws RemoteException{
        if(vehicleTypeController==null){
            vehicleTypeController=remoteFactory.getVehicleTypeController();
        }
        return vehicleTypeController;
    }
}
