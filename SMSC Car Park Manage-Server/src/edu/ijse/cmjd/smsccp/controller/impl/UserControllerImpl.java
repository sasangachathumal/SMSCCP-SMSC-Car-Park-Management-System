package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.UserController;
import edu.ijse.cmjd.smsccp.fileaccess.UserFileAccess;
import edu.ijse.cmjd.smsccp.model.Users;
import edu.ijse.cmjd.smsccp.observer.UserObserver;
import edu.ijse.cmjd.smsccp.observerble.UserObserverble;
import edu.ijse.cmjd.smsccp.reserve.UserReserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserControllerImpl extends UnicastRemoteObject implements UserController {

    private final UserFileAccess fileAccess = new UserFileAccess();
    private static final UserObserverble OBSERVERBLE = new UserObserverble();
    private static final UserReserver RESERVER = new UserReserver();
    
    public UserControllerImpl() throws RemoteException {
        
    }
    
    @Override
    public boolean addUser(Users user) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddUsers = fileAccess.addUsers(user);
        if (isaddUsers) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setUsersMessages(new String("new User Added !!!"));
                        OBSERVERBLE.setUsers(user);
                    } catch (RemoteException ex) {
                        Logger.getLogger(UserControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        return isaddUsers;
    }

    @Override
    public boolean updateUser(Users user) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveUser(user.getUserId(), this)) {
                boolean isupdateUsers = fileAccess.updateUsers(user);
                if (isupdateUsers) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setUsersMessages(new String("A User Update !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(UserControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isupdateUsers;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseUser(user.getUserId(), this);
        }
    }

    @Override
    public boolean deleteUser(String id) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reserveUser(id, this)) {
                boolean isdeleteUsers = fileAccess.deleteUsers(id);
                if (isdeleteUsers) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setUsersMessages(new String("A User Delete !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(UserControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeleteUsers;
            } else {
                return false;
            }
        } finally {
            RESERVER.releaseUser(id, this);
        }
    }

    @Override
    public Users searchUser(String id) throws RemoteException, ClassNotFoundException, IOException {
        return fileAccess.searchUsers(id);
    }

    @Override
    public ArrayList<Users> getAllUser() throws RemoteException, ClassNotFoundException, IOException {
        return fileAccess.getAllUsers();
    }

    @Override
    public boolean reserveUser(String id) throws RemoteException, IOException {
        return RESERVER.reserveUser(id, this);
    }

    @Override
    public boolean releaseUser(String id) throws RemoteException, IOException {
        return RESERVER.releaseUser(id, this);
    }

    @Override
    public void addUserObserver(UserObserver userObserver) throws RemoteException, IOException {
        OBSERVERBLE.setUserObserver(userObserver);
    }

    @Override
    public void removeUserObserver(UserObserver userObserver) throws RemoteException, IOException {
        OBSERVERBLE.removeUserObserver(userObserver);
    }
    
}
