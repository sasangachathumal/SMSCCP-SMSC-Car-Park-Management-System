package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.FixCustomerController;
import java.util.HashMap;
import java.util.Map;

public class FixCustomerReserver {

    private static final Map<String, FixCustomerController> reserveData = new HashMap<>();
    
    public boolean reserveFixCustomer(String id, FixCustomerController fixCustomerController){
        if(reserveData.containsKey(id)){
            return reserveData.get(id)==fixCustomerController;
        }else{
            reserveData.put(id, fixCustomerController);
            return true;
        }
    }
    public boolean releaseFixCustomer(String id, FixCustomerController fixCustomerController){
        if(reserveData.get(id)==fixCustomerController){
            reserveData.remove(id);
            return true;
        }else{
            return false;
        }
    }
    
}
