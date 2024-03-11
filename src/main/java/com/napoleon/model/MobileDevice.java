package com.napoleon.model;

import java.sql.Timestamp;

public class MobileDevice {
    private int deviceId;
    private int customerId;
    private String brand;
    private String model;
    private String serialNumber;
    private Timestamp submissionDate;

    // Standardkonstruktor
    public MobileDevice() {
    }

    // Fullständig konstruktor
    public MobileDevice(int deviceId, int customerId, String brand, String model, String serialNumber, Timestamp submissionDate) {
        this.deviceId = deviceId;
        this.customerId = customerId;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.submissionDate = submissionDate;
    }

    // Getters och Setters
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }
}
