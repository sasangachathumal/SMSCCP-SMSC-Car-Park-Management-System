package edu.ijse.cmjd.smsccp.controller.impl;

import edu.ijse.cmjd.smsccp.controller.PlaceController;
import edu.ijse.cmjd.smsccp.fileaccess.ParkingplaceFileAccess;
import edu.ijse.cmjd.smsccp.model.Place;
import edu.ijse.cmjd.smsccp.observer.PlaceObserver;
import edu.ijse.cmjd.smsccp.observerble.PlaceObserverble;
import edu.ijse.cmjd.smsccp.reserve.PlaceReserver;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceControllerimpl extends UnicastRemoteObject implements PlaceController{

    
    private final ParkingplaceFileAccess access = new ParkingplaceFileAccess();
    private static final PlaceObserverble OBSERVERBLE = new PlaceObserverble();
    private static final PlaceReserver RESERVER = new PlaceReserver();
    
    public PlaceControllerimpl() throws RemoteException {
        
    }
    
    @Override
    public boolean addPlace(Place place) throws RemoteException, ClassNotFoundException, IOException {
        boolean isaddPlace = access.addPlace(place);
        if (isaddPlace) {
            new Thread() {
                public void run() {
                    try {
                        OBSERVERBLE.setPlaceMessages(new String("New Place Added !!!"));
                        OBSERVERBLE.setPlace(place);
                    } catch (RemoteException ex) {
                        Logger.getLogger(PlaceControllerimpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
        return isaddPlace;
    }

    @Override
    public boolean updatePlace(Place place) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reservePlace(place.getPlaceID(), this)) {
                boolean isupdatePlace = access.updatePlace(place);
                if (isupdatePlace) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setPlaceMessages(new String("A Place Update !!!"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(PlaceControllerimpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isupdatePlace;
            } else {
                return false;
            }
        } finally {
            RESERVER.releasePlace(place.getPlaceID(), this);
        }
    }

    @Override
    public boolean deletePlace(String id) throws RemoteException, ClassNotFoundException, IOException {
        try {
            if (RESERVER.reservePlace(id, this)) {
                boolean isdeletePlace = access.deletePlace(id);
                if (isdeletePlace) {
                    new Thread() {
                        public void run() {
                            try {
                                OBSERVERBLE.setPlaceMessages(new String("A Place Delete"));
                            } catch (RemoteException ex) {
                                Logger.getLogger(PlaceControllerimpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
                return isdeletePlace;
            } else {
                return false;
            }
        } finally {
            RESERVER.releasePlace(id, this);
        }
    }

    @Override
    public Place searchPlace(String id) throws RemoteException, ClassNotFoundException, IOException {
        return access.searchPlace(id);
    }

    @Override
    public ArrayList<Place> getAllPlace() throws RemoteException, ClassNotFoundException, IOException {
        return access.getAllPlace();
    }

    @Override
    public boolean reservePlace(String id) throws RemoteException, IOException {
        return RESERVER.reservePlace(id, this);
    }

    @Override
    public boolean releasePlace(String id) throws RemoteException, IOException {
        return RESERVER.releasePlace(id, this);
    }

    @Override
    public void addPlaceObserver(PlaceObserver placeObserver) throws RemoteException, IOException {
        OBSERVERBLE.setPlaceObserver(placeObserver);
    }

    @Override
    public void removePlaceObserver(PlaceObserver placeObserver) throws RemoteException, IOException {
        OBSERVERBLE.removePlaceObserver(placeObserver);
    }
    
}
