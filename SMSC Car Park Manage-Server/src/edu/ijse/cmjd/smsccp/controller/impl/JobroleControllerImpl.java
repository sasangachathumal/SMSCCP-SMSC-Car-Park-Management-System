package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.JobroleController;
import edu.ijse.cmjd.smsccp.fileaccess.JobroleFileAccess;
import edu.ijse.cmjd.smsccp.model.JobRole;
import edu.ijse.cmjd.smsccp.observer.JobRoleObserver;
import edu.ijse.cmjd.smsccp.observerble.JobRoleObserverble;
import edu.ijse.cmjd.smsccp.reserve.JobroleReserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobroleControllerImpl extends UnicastRemoteObject implements JobroleController {

    private final JobroleFileAccess access = new JobroleFileAccess();
    private static final JobRoleObserverble OBSERVERBLE = new JobRoleObserverble();
    private static final JobroleReserver RESERVER = new JobroleReserver();
    
    
    public JobroleControllerImpl() throws RemoteException {
        
    }
    
    @Override
    public boolean addJobRole(JobRole jobRole) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddJobRole = access.addJobRole(jobRole);
        if (isaddJobRole) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setMessage(new String("New JobRole Add !!!"));
                        OBSERVERBLE.setJobrole(jobRole);
                    } catch (RemoteException ex) {
                        Logger.getLogger(JobroleControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        return isaddJobRole;
    }

    @Override
    public boolean updateJobRole(JobRole jobRole) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveJobrole(jobRole.getJobRoleName(), this)) {
                boolean isupdateJobRole = access.updateJobRole(jobRole);
                if (isupdateJobRole) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setMessage(new String("A JobRole Update !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(JobroleControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isupdateJobRole;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseJobrole(jobRole.getJobRoleName(), this);
        }
    }

    @Override
    public boolean deleteJobRole(String nic) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveJobrole(nic, this)) {
                boolean isdeleteJobRole = access.deleteJobRole(nic);
                if (isdeleteJobRole) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setMessage(new String("A JobRole Delete !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(JobroleControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeleteJobRole;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseJobrole(nic, this);
        }
    }

    @Override
    public JobRole searchJobRole(String nic) throws RemoteException, ClassNotFoundException, IOException {
        return access.searchJobRole(nic);
    }

    @Override
    public ArrayList<JobRole> getAllJobRole() throws RemoteException, ClassNotFoundException, IOException {
        return access.getAllJobRoles();
    }

    @Override
    public void addJobRoleObserver(JobRoleObserver jobRoleObserver) throws RemoteException, IOException {
        OBSERVERBLE.addJobroleObserver(jobRoleObserver);
    }

    @Override
    public void removeJobRoleObserver(JobRoleObserver jobRoleObserver) throws RemoteException, IOException {
        OBSERVERBLE.removeJobroleObserver(jobRoleObserver);
    }

    @Override
    public boolean reserveJobrole(String id) throws RemoteException, IOException {
        return RESERVER.reserveJobrole(id, this);
    }

    @Override
    public boolean releaseJobrole(String id) throws RemoteException, IOException {
        return RESERVER.releaseJobrole(id, this);
    }
    
}
