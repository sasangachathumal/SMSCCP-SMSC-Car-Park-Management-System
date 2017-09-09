package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.CustomerParkingController;
import java.util.HashMap;
import java.util.Map;

public class CustomerParkingReserver {
    
    private static final Map<String, CustomerParkingController> reserveData = new HashMap<>();
    
    public boolean reserveCustomerParking(String id, CustomerParkingController customerParkingController){
        if(reserveData.containsKey(id)){
            return reserveData.get(id)==customerParkingController;
        }else{
            reserveData.put(id, customerParkingController);
            return true;
        }
    }
    public boolean releaseCustomerParking(String id, CustomerParkingController customerParkingController){
        if(reserveData.get(id)==customerParkingController){
            reserveData.remove(id);
            return true;
        }else{
            return false;
        }
    }
    
}
