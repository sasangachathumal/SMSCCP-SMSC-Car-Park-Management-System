package edu.ijse.cmjd.smsccp.observerble;

import edu.ijse.cmjd.smsccp.model.Place;
import edu.ijse.cmjd.smsccp.observer.PlaceObserver;
import java.rmi.*;
import java.util.*;

public class PlaceObserverble {
    
    private String message;
    
    private Place place;
    
    private static ArrayList<PlaceObserver> observerlist = new ArrayList<>();
    
    public void setPlaceObserver(PlaceObserver placeObserver) {
        observerlist.add(placeObserver);
    }
    
    public void removePlaceObserver(PlaceObserver placeObserver) {
        observerlist.remove(placeObserver);
    }
    
    public void notifyPlaceMessages() throws RemoteException {
        for (PlaceObserver customerparkingObserver : observerlist) {
            customerparkingObserver.sentMessage(message);
        }
    }

    public void notifyPlaces() throws RemoteException {
        for (PlaceObserver customerparkingObserver : observerlist) {
            customerparkingObserver.sentPlace(place);
        }
    }
    
    public void setPlace(Place place) throws RemoteException {
        if(this.place != place){
            this.place = place;
            notifyPlaces();
        }
    }
    
    public void setPlaceMessages(String message) throws RemoteException {
        if(this.message != message){
            this.message = message;
            notifyPlaceMessages();
        }
    }
    
}
