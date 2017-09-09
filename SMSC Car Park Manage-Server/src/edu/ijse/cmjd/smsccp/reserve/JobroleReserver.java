package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.JobroleController;
import java.util.HashMap;
import java.util.Map;

public class JobroleReserver {
    
    private static final Map<String, JobroleController> reserveData = new HashMap<>();
    
    public boolean reserveJobrole(String id, JobroleController jobroleController){
        if(reserveData.containsKey(id)){
            return reserveData.get(id)==jobroleController;
        }else{
            reserveData.put(id, jobroleController);
            return true;
        }
    }
    public boolean releaseJobrole(String id, JobroleController jobroleController){
        if(reserveData.get(id)==jobroleController){
            reserveData.remove(id);
            return true;
        }else{
            return false;
        }
    }
    
}
