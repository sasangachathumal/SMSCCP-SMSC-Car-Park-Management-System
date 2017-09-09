/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.JobRole;
import edu.ijse.cmjd.smsccp.observer.JobRoleObserver;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author U s E r™
 */
public interface JobroleController extends Remote{
    
   public boolean addJobRole(JobRole jobRole)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updateJobRole(JobRole jobRole)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deleteJobRole(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public JobRole searchJobRole(String nic)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<JobRole> getAllJobRole()throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reserveJobrole(String id) throws RemoteException, IOException;
    
    public boolean releaseJobrole(String id) throws RemoteException, IOException;
    
    public void addJobRoleObserver(JobRoleObserver jobRoleObserver)throws 
            RemoteException, IOException;
    
    public void removeJobRoleObserver(JobRoleObserver jobRoleObserver)throws 
            RemoteException, IOException;
}
