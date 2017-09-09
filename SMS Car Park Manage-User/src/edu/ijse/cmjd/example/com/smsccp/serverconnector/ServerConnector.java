package edu.ijse.cmjd.example.com.smsccp.serverconnector;

import edu.ijse.cmjd.example.com.smsccp.controller.CustomerController;
import edu.ijse.cmjd.example.com.smsccp.controller.CustomerParkingController;
import edu.ijse.cmjd.example.com.smsccp.controller.FixCustomerController;
import edu.ijse.cmjd.example.com.smsccp.controller.JobroleController;
import edu.ijse.cmjd.example.com.smsccp.controller.PlaceController;
import edu.ijse.cmjd.example.com.smsccp.controller.SectionController;
import edu.ijse.cmjd.example.com.smsccp.controller.UserController;
import edu.ijse.cmjd.example.com.smsccp.controller.VehicleTypeController;
import edu.ijse.cmjd.example.com.smsccp.remotefactory.RemoteFactory;
import java.net.*;
import java.rmi.*;

public class ServerConnector {
    
    private static ServerConnector serverConnector;
    private CustomerController customerController;
    private CustomerParkingController customerParkingController;
    private FixCustomerController fixCustomerController;
    private PlaceController placeController;
    private RemoteFactory remoteFactory;
    
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
}
