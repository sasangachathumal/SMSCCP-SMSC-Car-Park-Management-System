package edu.ijse.cmjd.smsccp.model;

import java.io.*;

public class Customer implements Serializable {
    private String customerName;
    private String customerNIC;
    private String customerTelephone;
    private String customerVehicalNo;

    public Customer() {
    }
    
    /**
     * @param customerName
     * @param customerNIC 
     * @param customerTelephone 
     * @param customerVehicalNo 
     */

    public Customer(String customerName, String customerNIC, String customerTelephone, String customerVehicalNo) {
                this.customerName = customerName;
                this.customerNIC = customerNIC;
                this.customerTelephone = customerTelephone;
                this.customerVehicalNo = customerVehicalNo;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerNIC
     */
    public String getCustomerNIC() {
        return customerNIC;
    }

    /**
     * @param customerNIC 
     */
    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
    }

    /**
     * @return the customerTelephone
     */
    public String getCustomerTelephone() {
        return customerTelephone;
    }

    /**
     * @param customerTelephone
     */
    public void setCustomerTelephone(String customerTelephone) {
        this.customerTelephone = customerTelephone;
    }

    /**
     * @return the customerVehicalNo
     */
    public String getCustomerVehicalNo() {
        return customerVehicalNo;
    }

    /**
     * @param customerVehicalNo
     */
    public void setCustomerVehicalNo(String customerVehicalNo) {
        this.customerVehicalNo = customerVehicalNo;
    }
    
    
}
