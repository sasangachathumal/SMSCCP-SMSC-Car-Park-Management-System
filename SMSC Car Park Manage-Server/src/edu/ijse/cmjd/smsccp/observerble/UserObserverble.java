package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.Users;
import edu.ijse.cmjd.smsccp.observer.UserObserver;
import java.rmi.*;
import java.util.*;

public class UserObserverble {
    
    private String message;
    
    private Users users;
    
    private static ArrayList<UserObserver> observerlist = new ArrayList<>();
    
    public void setUserObserver(UserObserver userObserver) {
        observerlist.add(userObserver);
    }
    
    public void removeUserObserver(UserObserver userObserver) {
        observerlist.remove(userObserver);
    }
    
    public void notifyUsersMessages() throws RemoteException {
        for (UserObserver customerparkingObserver : observerlist) {
            customerparkingObserver.sentMessage(message);
        }
    }

    public void notifyUserss() throws RemoteException {
        for (UserObserver customerparkingObserver : observerlist) {
            customerparkingObserver.sentUser(users);
        }
    }
    
    public void setUsers(Users users) throws RemoteException {
        if(this.users != users){
            this.users = users;
            notifyUserss();
        }
    }
    
    public void setUsersMessages(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifyUsersMessages();
        }
    }
    
}
