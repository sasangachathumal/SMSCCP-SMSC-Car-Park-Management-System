package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.PlaceController;
import java.util.HashMap;
import java.util.Map;

public class PlaceReserver {

    private static final Map<String, PlaceController> reserveData = new HashMap<>();

    public boolean reservePlace(String id, PlaceController placeController) {
        System.out.println(reserveData.containsKey(id));
        if (reserveData.containsKey(id)) {
            return reserveData.get(id) == placeController;
        } else {
            reserveData.put(id, placeController);
            System.out.println("Added");
            return true;
        }
    }

    public boolean releasePlace(String id, PlaceController placeController) {
        System.out.println(reserveData.containsKey(id)+"rel");
        if (reserveData.get(id) == placeController) {
            reserveData.remove(id);
            System.out.println("removed ");
            return true;
        } else {
            return false;
        }
    }
    
   

}
