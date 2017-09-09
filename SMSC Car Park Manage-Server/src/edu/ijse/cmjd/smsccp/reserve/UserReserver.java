package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.UserController;
import java.util.HashMap;
import java.util.Map;

public class UserReserver {
    
    private static final Map<String, UserController> reserveData = new HashMap<>();
    
    public boolean reserveUser(String id, UserController userController){
        if(reserveData.containsKey(id)){
            return reserveData.get(id)==userController;
        }else{
            reserveData.put(id, userController);
            return true;
        }
    }
    public boolean releaseUser(String id, UserController userController){
        if(reserveData.get(id)==userController){
            reserveData.remove(id);
            return true;
        }else{
            return false;
        }
    }
    
}
