/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ijse.cmjd.smsccp.remotefactory.impl;

import edu.ijse.cmjd.smsccp.controller.CustomerController;
import edu.ijse.cmjd.smsccp.controller.CustomerParkingController;
import edu.ijse.cmjd.smsccp.controller.FixCustomerController;
import edu.ijse.cmjd.smsccp.controller.JobroleController;
import edu.ijse.cmjd.smsccp.controller.PlaceController;
import edu.ijse.cmjd.smsccp.controller.SectionController;
import edu.ijse.cmjd.smsccp.controller.UserController;
import edu.ijse.cmjd.smsccp.controller.VehicleTypeController;
import edu.ijse.cmjd.smsccp.controller.impl.CustomerControllerImpl;
import edu.ijse.cmjd.smsccp.controller.impl.CustomerParkingControllerImpl;
import edu.ijse.cmjd.smsccp.controller.impl.FixCustomerControllerImpl;
import edu.ijse.cmjd.smsccp.controller.impl.JobroleControllerImpl;
import edu.ijse.cmjd.smsccp.controller.impl.PlaceControllerimpl;
import edu.ijse.cmjd.smsccp.controller.impl.SectionControllerImpl;
import edu.ijse.cmjd.smsccp.controller.impl.UserControllerImpl;
import edu.ijse.cmjd.smsccp.controller.impl.VehicaleTypeControllerImpl;
import edu.ijse.cmjd.smsccp.remotefactory.RemoteFactory;
import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author linux
 */
public class RemoteFactoryImpl extends UnicastRemoteObject implements RemoteFactory{
    public RemoteFactoryImpl()throws RemoteException{
        
    }

    @Override
    public CustomerController getCustomerController() throws RemoteException {
        return new CustomerControllerImpl();
    }

    @Override
    public CustomerParkingController getCustomerParkingController() throws RemoteException {
        return new CustomerParkingControllerImpl();
    }

    @Override
    public FixCustomerController getFixCustomerController() throws RemoteException {
        return new FixCustomerControllerImpl();
    }

    @Override
    public PlaceController getPlaceController() throws RemoteException {
        return new PlaceControllerimpl();
    }

    @Override
    public UserController getUserController() throws RemoteException {
        return new UserControllerImpl();
    }

    @Override
    public JobroleController getJobroleController() throws RemoteException {
        return new JobroleControllerImpl();
    }

    @Override
    public SectionController getSectionController() throws RemoteException {
        return new SectionControllerImpl();
    }

    @Override
    public VehicleTypeController getVehicleTypeController() throws RemoteException {
        return new VehicaleTypeControllerImpl();
    }
}
