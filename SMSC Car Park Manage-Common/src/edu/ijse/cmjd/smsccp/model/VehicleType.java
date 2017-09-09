package edu.ijse.cmjd.smsccp.model;

public class VehicleType {
    private String vehicleType;
    private String date;

    public VehicleType() {
    }

    public VehicleType(String vehicleType, String date) {
        this.vehicleType = vehicleType;
        this.date = date;
    }

    /**
     * @return vehicleType
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
}
