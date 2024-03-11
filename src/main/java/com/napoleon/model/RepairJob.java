package com.napoleon.model;

import java.sql.Timestamp;

public class RepairJob {
    private int jobId;
    private int deviceId;
    private String problemDescription;
    private String repairStatus;
    private Timestamp estimatedCompletionDate;
    private Timestamp completionDate;

    // Standardkonstruktor
    public RepairJob() {
    }

    // Fullständig konstruktor
    public RepairJob(int jobId, int deviceId, String problemDescription, String repairStatus, Timestamp estimatedCompletionDate, Timestamp completionDate) {
        this.jobId = jobId;
        this.deviceId = deviceId;
        this.problemDescription = problemDescription;
        this.repairStatus = repairStatus;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.completionDate = completionDate;
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

    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }
}
