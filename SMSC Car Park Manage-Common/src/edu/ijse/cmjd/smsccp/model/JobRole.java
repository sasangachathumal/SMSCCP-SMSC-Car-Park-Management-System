package edu.ijse.cmjd.smsccp.model;

import java.io.Serializable;

public class JobRole implements Serializable{
    private String jobRoleName;
    private String date;

    public JobRole() {
    }

    public JobRole(String jobRoleName, String date) {
        this.jobRoleName = jobRoleName;
        this.date = date;
    }

    /**
     * @return jobRoleName
     */
    public String getJobRoleName() {
        return jobRoleName;
    }

    /**
     * @param jobRoleName
     */
    public void setJobRoleName(String jobRoleName) {
        this.jobRoleName = jobRoleName;
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
