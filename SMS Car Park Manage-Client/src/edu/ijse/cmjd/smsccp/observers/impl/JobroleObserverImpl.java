package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.JobRole;
import edu.ijse.cmjd.smsccp.observer.JobRoleObserver;
import edu.ijse.cmjd.smsccp.view.InputCustomers;
import edu.ijse.cmjd.smsccp.view.inputJobRole;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class JobroleObserverImpl extends UnicastRemoteObject implements JobRoleObserver {

    private inputJobRole inputjobRole;
    private InputCustomers inputCustomers;
    
    public JobroleObserverImpl(InputCustomers inputCustomers) throws RemoteException {
        this.inputCustomers = inputCustomers;
    }
    
    public JobroleObserverImpl(inputJobRole inputjobRole) throws RemoteException {
        this.inputjobRole = inputjobRole;
    }
    
    @Override
    public void sentMessage(String message) throws RemoteException {
        inputCustomers.loadJobRoles();
        inputjobRole.setMessageForNewJobrole(message);
    }

    @Override
    public void sentJobrole(JobRole jobRole) throws RemoteException {
        inputCustomers.setnewJobrole(jobRole);
    }
    
}
