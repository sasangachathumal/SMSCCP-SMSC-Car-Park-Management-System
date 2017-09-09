package edu.ijse.cmjd.smsccp.reserve;

import edu.ijse.cmjd.smsccp.controller.SectionController;
import java.util.HashMap;
import java.util.Map;

public class SectionReserver {
    
    private static final Map<String, SectionController> reserveData = new HashMap<>();
    
    public boolean reserveSection(String id, SectionController sectionController){
        if(reserveData.containsKey(id)){
            return reserveData.get(id)==sectionController;
        }else{
            reserveData.put(id, sectionController);
            return true;
        }
    }
    public boolean releaseSection(String id, SectionController sectionController){
        if(reserveData.get(id)==sectionController){
            reserveData.remove(id);
            return true;
        }else{
            return false;
        }
    }
    
}
