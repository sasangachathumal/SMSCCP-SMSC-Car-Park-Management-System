package edu.ijse.cmjd.smsccp.observer;

import edu.ijse.cmjd.smsccp.model.Place;
import java.rmi.*;

public interface PlaceObserver extends Remote {
    
    public void sentMessage(String message) throws RemoteException;
    
    public void sentPlace(Place place) throws RemoteException;
    
}
