package edu.ijse.cmjd.smsccp.controller;

import edu.ijse.cmjd.smsccp.model.Place;
import edu.ijse.cmjd.smsccp.observer.PlaceObserver;
import java.io.*;
import java.rmi.*;
import java.util.*;

public interface PlaceController extends Remote {
    
    public boolean addPlace(Place place)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean updatePlace(Place place)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean deletePlace(String id)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public Place searchPlace(String id)throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public ArrayList<Place> getAllPlace()throws RemoteException, 
            ClassNotFoundException, IOException;
    
    public boolean reservePlace(String id) throws RemoteException, IOException;
    
    public boolean releasePlace(String id) throws RemoteException, IOException;
    
    public void addPlaceObserver(PlaceObserver placeObserver)throws 
            RemoteException, IOException;
    
    public void removePlaceObserver(PlaceObserver placeObserver)throws 
            RemoteException, IOException;

}
