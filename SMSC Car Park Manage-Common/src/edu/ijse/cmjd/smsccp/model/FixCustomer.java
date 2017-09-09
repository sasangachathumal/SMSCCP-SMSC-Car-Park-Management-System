package edu.ijse.cmjd.smsccp.model;

import java.io.*;

public class FixCustomer implements Serializable {
    private String FxCustomerName;
    private String FxCustomerINC;
    private String FxCustomerJobRole;
    private String FxCustomerSection;
    private String FxCustomerTelephone;

    public FixCustomer() {
    }

    /**
     * @param FxCustomerName
     * @param FxCustomerINC
     * @param FxCustomerJobRole
     * @param FxCustomerSection
     * @param FxCustomerTelephone
     */
    
    public FixCustomer(String FxCustomerName, String FxCustomerINC, 
            String FxCustomerJobRole, String FxCustomerSection, 
            String FxCustomerTelephone) {
                this.FxCustomerName = FxCustomerName;
                this.FxCustomerINC = FxCustomerINC;
                this.FxCustomerJobRole = FxCustomerJobRole;
                this.FxCustomerSection = FxCustomerSection;
                this.FxCustomerTelephone = FxCustomerTelephone;
    }

    /**
     * @return FxCustomerName
     */
    public String getFxCustomerName() {
        return FxCustomerName;
    }

    /**
     * @param FxCustomerName
     */
    public void setFxCustomerName(String FxCustomerName) {
        this.FxCustomerName = FxCustomerName;
    }

    /**
     * @return FxCustomerINC
     */
    public String getFxCustomerINC() {
        return FxCustomerINC;
    }

    /**
     * @param FxCustomerINC
     */
    public void setFxCustomerINC(String FxCustomerINC) {
        this.FxCustomerINC = FxCustomerINC;
    }

    /**
     * @return  FxCustomerJobRole
     */
    public String getFxCustomerJobRole() {
        return FxCustomerJobRole;
    }

    /**
     * @param FxCustomerJobRole
     */
    public void setFxCustomerJobRole(String FxCustomerJobRole) {
        this.FxCustomerJobRole = FxCustomerJobRole;
    }

    /**
     * @return FxCustomerSection
     */
    public String getFxCustomerSection() {
        return FxCustomerSection;
    }

    /**
     * @param FxCustomerSection
     */
    public void setFxCustomerSection(String FxCustomerSection) {
        this.FxCustomerSection = FxCustomerSection;
    }

    /**
     * @return FxCustomerTelephone
     */
    public String getFxCustomerTelephone() {
        return FxCustomerTelephone;
    }

    /**
     * @param FxCustomerTelephone
     */
    public void setFxCustomerTelephone(String FxCustomerTelephone) {
        this.FxCustomerTelephone = FxCustomerTelephone;
    }
    
    
}
