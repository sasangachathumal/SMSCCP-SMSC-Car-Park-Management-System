package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.JobRole;
import edu.ijse.cmjd.smsccp.observer.JobRoleObserver;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class JobRoleObserverble {
    private String message;
    
    private JobRole jobRole;
    
    private static ArrayList<JobRoleObserver> observerList = new ArrayList<>();
    
    public void addJobroleObserver(JobRoleObserver jobRoleObserver) {
        observerList.add(jobRoleObserver);
    }
    
    public void removeJobroleObserver(JobRoleObserver jobRoleObserver) {
        observerList.remove(jobRoleObserver);
    }
    
    public void notifyJobroleMessages() throws RemoteException {
        for (JobRoleObserver jobRoleObserver : observerList) {
            jobRoleObserver.sentMessage(message);
        }
    }
    
    public void notifyJobrole() throws RemoteException {
        for (JobRoleObserver jobRoleObserver : observerList) {
            jobRoleObserver.sentJobrole(jobRole);
        }
    }
    
    public void setMessage(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifyJobroleMessages();
        }
    }
    
    public void setJobrole(JobRole jobRole) throws RemoteException {
        if(this.jobRole != jobRole){
            this.jobRole = jobRole;
            notifyJobrole();
        }
    }
}
