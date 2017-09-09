package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.CustomerController;
import java.util.HashMap;
import java.util.Map;

public class CustomerReserver {
    
    private static final Map<String, CustomerController> reserveData = new HashMap<>();
    
    public boolean reserveCustomer(String id, CustomerController customerController){
        if(reserveData.containsKey(id)){
            return reserveData.get(id)==customerController;
        }else{
            reserveData.put(id, customerController);
            return true;
        }
    }
    public boolean releaseCustomer(String id, CustomerController customerController){
        if(reserveData.get(id)==customerController){
            reserveData.remove(id);
            return true;
        }else{
            return false;
        }
    }
    
}
