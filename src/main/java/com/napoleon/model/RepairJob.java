package com.napoleon.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;


public class RepairJob {
    private int jobId;
    private int deviceId;
    private String problemDescription;
    private String repairStatus;
    private Timestamp estimatedCompletionDate;
    private Timestamp completionDate;
    private String notes;

    // Fullständig konstruktor inklusive jobId för uppdateringar
    public RepairJob(int jobId, int deviceId, String problemDescription, String repairStatus, Timestamp estimatedCompletionDate, Timestamp completionDate, String notes) {
        this.jobId = jobId;
        this.deviceId = deviceId;
        this.problemDescription = problemDescription;
        this.repairStatus = repairStatus;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.completionDate = completionDate;
        this.notes = notes;
    }


    // Standardkonstruktor
    public RepairJob(int deviceId, String problemDescription, String repairStatus, Timestamp estimatedCompletionDate, Timestamp completionDate, String notes) {
        this.deviceId = deviceId;
        this.problemDescription = problemDescription;
        this.repairStatus = repairStatus;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.completionDate = completionDate;
        this.notes = notes;
    }



    // Getters och Setters
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    // Setter för repairStatus
    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    // Getter för estimatedCompletionDate
    public Timestamp getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    // Setter för estimatedCompletionDate
    public void setEstimatedCompletionDate(Timestamp estimatedCompletionDate) {
        this.estimatedCompletionDate = estimatedCompletionDate;
    }


    // Getter för notes
    public String getNotes() {
        return notes;
    }
    // Setter för notes
    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public String toString() {
        return "Job ID: " + jobId +
                ", Device ID: " + deviceId +
                ", Problem: " + problemDescription +
                ", Repair Status: " + (repairStatus != null ? repairStatus : "N/A") +
                ", Estimated Completion Date: " + (estimatedCompletionDate != null ? estimatedCompletionDate.toString() : "N/A") +
                ", Completion Date: " + (completionDate != null ? completionDate.toString() : "N/A") +
                ", Notes: " + (notes != null ? notes : "N/A");
    }
}

