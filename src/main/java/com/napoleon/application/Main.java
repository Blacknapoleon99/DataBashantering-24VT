package com.napoleon.application;



import com.napoleon.dao.CustomerDAO;
import com.napoleon.dao.MobileDeviceDAO;
import com.napoleon.dao.RepairJobDAO;
import com.napoleon.model.Customer;
import com.napoleon.model.MobileDevice;
import com.napoleon.model.RepairJob;
import com.napoleon.util.ConnectionManager;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // DAO-instanser
            CustomerDAO customerDao = new CustomerDAO();
            MobileDeviceDAO mobileDeviceDao = new MobileDeviceDAO();
            RepairJobDAO repairJobDao = new RepairJobDAO();

            // Skapa och lägg till flera kunder
            Customer johnDoe = new Customer("John Doe", "john.doe@example.com", "555-0123", "123 Main St");
            int johnDoeId = customerDao.insertCustomer(johnDoe); // Antag att insertCustomer returnerar genererat ID

            Customer janeSmith = new Customer("Jane Smith", "jane.smith@example.com", "555-4567", "456 Elm St");
            int janeSmithId = customerDao.insertCustomer(janeSmith); // Antag att insertCustomer returnerar genererat ID

            // Skapa och lägg till mobila enheter och associera dem med kunder
            MobileDevice johnDevice = new MobileDevice(johnDoeId, "BrandX", "ModelY", "SN123456", new Timestamp(System.currentTimeMillis()));
            mobileDeviceDao.insertMobileDevice(johnDevice);

            MobileDevice janeDevice = new MobileDevice(janeSmithId, "BrandA", "ModelB", "SN789012", new Timestamp(System.currentTimeMillis()));
            mobileDeviceDao.insertMobileDevice(janeDevice);

            // Notera: Implementera logik för att hantera enhets-ID:er om det behövs

            // Skapa och lägg till flera reparationsjobb
            // Antag att vi hanterar enhets-ID:er korrekt här
            RepairJob johnRepairJob = new RepairJob(johnDevice.getDeviceId(), "Cracked screen", "received", new Timestamp(System.currentTimeMillis()), null, "No additional notes");
            repairJobDao.insertRepairJob(johnRepairJob);

            RepairJob janeRepairJob = new RepairJob(janeDevice.getDeviceId(), "Battery replacement", "in_progress", new Timestamp(System.currentTimeMillis()), null, "Customer requests OEM battery");
            repairJobDao.insertRepairJob(janeRepairJob);



            // Visa information om alla poster
            System.out.println("Alla kunder:");
            customerDao.getAllCustomers().forEach(System.out::println);

            System.out.println("Alla mobila enheter:");
            mobileDeviceDao.getAllMobileDevices().forEach(System.out::println);

            System.out.println("Alla reparationsjobb:");
            repairJobDao.getAllRepairJobs().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MobileDevice> devices = new ArrayList<>();
        devices.add(new MobileDevice(1, "BrandX", "ModelY", "SN123456", new Timestamp(System.currentTimeMillis())));
        devices.add(new MobileDevice(2, "BrandA", "ModelB", "SN789012", new Timestamp(System.currentTimeMillis())));

        for (MobileDevice device : devices) {
            if (device.getBrand() == null || device.getBrand().isEmpty()) {
                System.out.println("Brand can't be null or empty for device");
                continue; // Hoppa över denna iteration om brand är null eller tom
            }

        }
    }
}
