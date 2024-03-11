package com.napoleon.model;

import java.sql.Timestamp;

public class RepairJob {
    private int jobId;
    private int deviceId;
    private String problemDescription;
    private String repairStatus;
    private Timestamp estimatedCompletionDate;
    private Timestamp completionDate;
    private String status;
    private String notes;
    private String SubmissionDate;



    // Standardkonstruktor
    public RepairJob(int deviceId, String problemDescription, String repairStatus, Timestamp estimatedCompletionDate, Timestamp completionDate, String notes) {
        this.deviceId = deviceId;
        this.problemDescription = problemDescription;
        this.repairStatus = repairStatus;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.completionDate = completionDate;
        this.notes = notes;
    }

    // Fullständig konstruktor
    public RepairJob(int jobId, int deviceId, String problemDescription, String repairStatus, Timestamp estimatedCompletionDate, Timestamp completionDate, String notes) {
        this.jobId = jobId;
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

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Timestamp getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    public void setEstimatedCompletionDate(Timestamp estimatedCompletionDate) {
        this.estimatedCompletionDate = estimatedCompletionDate;


    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getSubmissionDate() {
        return SubmissionDate;
    }

    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public String toString() {
        return "RepairJob{" +
                "jobId=" + jobId +
                ", deviceId=" + deviceId +
                ", problemDescription='" + problemDescription + '\'' +
                ", repairStatus='" + repairStatus + '\'' +
                ", estimatedCompletionDate=" + estimatedCompletionDate +
                ", completionDate=" + completionDate +
                '}';
    }

}

