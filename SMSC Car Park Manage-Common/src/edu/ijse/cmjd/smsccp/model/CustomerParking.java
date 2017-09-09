package edu.ijse.cmjd.smsccp.model;

import java.io.*;

public class CustomerParking implements Serializable {
    private String placeId;
    private String CustomerNIC;
    private String Date;
    private String ArrivalTime;
    private String LeaveTime;

    public CustomerParking() {
    }

    /**
     *
     * @param placeId
     * @param CustomerNIC
     * @param Date
     * @param ArrivalTime
     * @param LeaveTime
     */
    public CustomerParking(String placeId, String CustomerNIC, String Date, String ArrivalTime, String LeaveTime) {
        this.placeId = placeId;
        this.CustomerNIC = CustomerNIC;
        this.Date = Date;
        this.ArrivalTime = ArrivalTime;
        this.LeaveTime = LeaveTime;
    }

    /**
     * @return placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * @param placeId
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * @return CustomerNIC
     */
    public String getCustomerNIC() {
        return CustomerNIC;
    }

    /**
     * @param CustomerNIC
     */
    public void setCustomerNIC(String CustomerNIC) {
        this.CustomerNIC = CustomerNIC;
    }

    /**
     * @return Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     * @return ArrivalTime
     */
    public String getArrivalTime() {
        return ArrivalTime;
    }

    /**
     * @param ArrivalTime
     */
    public void setArrivalTime(String ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    /**
     * @return LeaveTime
     */
    public String getLeaveTime() {
        return LeaveTime;
    }

    /**
     * @param LeaveTime
     */
    public void setLeaveTime(String LeaveTime) {
        this.LeaveTime = LeaveTime;
    }
    
    
}
