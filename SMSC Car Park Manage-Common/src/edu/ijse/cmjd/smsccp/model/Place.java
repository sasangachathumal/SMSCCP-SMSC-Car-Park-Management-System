package edu.ijse.cmjd.smsccp.model;

import java.io.*;

public class Place implements Serializable {
    private String PlaceID;
    private String PlaceVehicalType;
    private String PlaceSpecial;

    public Place() {
    }

    /**
     * @param PlaceID
     * @param PlaceVehicalType
     * @param PlaceSpecial
     */
    
    public Place(String PlaceID, String PlaceVehicalType, String PlaceSpecial) {
        this.PlaceID = PlaceID;
        this.PlaceVehicalType = PlaceVehicalType;
        this.PlaceSpecial = PlaceSpecial;
    }

    /**
     * @return PlaceID
     */
    public String getPlaceID() {
        return PlaceID;
    }

    /**
     * @param PlaceID
     */
    public void setPlaceID(String PlaceID) {
        this.PlaceID = PlaceID;
    }

    /**
     * @return PlaceVehicalType
     */
    public String getPlaceVehicalType() {
        return PlaceVehicalType;
    }

    /**
     * @param PlaceVehicalType
     */
    public void setPlaceVehicalType(String PlaceVehicalType) {
        this.PlaceVehicalType = PlaceVehicalType;
    }

    /**
     * @return PlaceSpecial
     */
    public String getPlaceSpecial() {
        return PlaceSpecial;
    }

    /**
     * @param PlaceSpecial
     */
    public void setPlaceSpecial(String PlaceSpecial) {
        this.PlaceSpecial = PlaceSpecial;
    }
    
    
}
