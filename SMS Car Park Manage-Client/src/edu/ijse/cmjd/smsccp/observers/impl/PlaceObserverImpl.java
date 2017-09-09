package edu.ijse.cmjd.smsccp.observers.impl;

import edu.ijse.cmjd.smsccp.model.Place;
import edu.ijse.cmjd.smsccp.observer.PlaceObserver;
import edu.ijse.cmjd.smsccp.view.InputPlace;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PlaceObserverImpl extends UnicastRemoteObject implements PlaceObserver {

    private InputPlace inputPlace;
    
    public PlaceObserverImpl(InputPlace inputPlace) throws RemoteException {
        this.inputPlace = inputPlace;
    }
    
    @Override
    public void sentMessage(String message) throws RemoteException {
        inputPlace.loadPlace();
    }

    @Override
    public void sentPlace(Place place) throws RemoteException {
        
    }
    
}
