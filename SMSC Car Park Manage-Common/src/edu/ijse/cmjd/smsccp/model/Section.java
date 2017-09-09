package edu.ijse.cmjd.smsccp.model;

import java.io.Serializable;

public class Section implements Serializable{
    private String sectionName;
    private String date;

    public Section() {
    }

    public Section(String sectionName, String date) {
        this.sectionName = sectionName;
        this.date = date;
    }

    /**
     * @return sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @param sectionName
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
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
