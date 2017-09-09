package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.VehicleTypeController;
import java.util.HashMap;
import java.util.Map;

public class VehicaleTypeReserver {
    
    private static final Map<String, VehicleTypeController> reserveData = new HashMap<>();
    
    public boolean reserveVehicaleTpe(String id, VehicleTypeController vehicleTypeController){
        if(reserveData.containsKey(id)){
            return reserveData.get(id)==vehicleTypeController;
        }else{
            reserveData.put(id, vehicleTypeController);
            return true;
        }
    }
    public boolean releaseVehicaleType(String id, VehicleTypeController vehicleTypeController){
        if(reserveData.get(id)==vehicleTypeController){
            reserveData.remove(id);
            return true;
        }else{
            return false;
        }
    }
    
}
