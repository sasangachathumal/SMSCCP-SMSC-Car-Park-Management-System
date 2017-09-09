package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.Users;
import edu.ijse.cmjd.smsccp.observer.UserObserver;
import java.io.*;
import java.rmi.*;
import java.util.*;

public interface UserController extends Remote {
    
    public boolean addUser(Users user)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updateUser(Users user)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deleteUser(String id)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public Users searchUser(String id)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<Users> getAllUser()throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reserveUser(String id) throws RemoteException, IOException;
    
    public boolean releaseUser(String id) throws RemoteException, IOException;
    
    public void addUserObserver(UserObserver userObserver)throws 
            RemoteException, IOException;
    
    public void removeUserObserver(UserObserver userObserver)throws 
            RemoteException, IOException;
    
}
