package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.JobRole;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JobRoleObserver extends Remote{
    public void sentMessage(String message) throws RemoteException;
    public void sentJobrole(JobRole jobRole) throws RemoteException;
}
