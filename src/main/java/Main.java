import java.sql.Timestamp;


public class Main {
    public static void main(String[] args) {
        CustomerDAO customerDao = new CustomerDAO();
        MobileDeviceDAO mobileDeviceDao = new MobileDeviceDAO();
        RepairJobDAO repairJobDao = new RepairJobDAO();

        //  skapa en ny kund
        Customer newCustomer = new Customer(0, "John Doe", "john.doe@example.com", "555-0123", "123 Main St");
        boolean customerInserted = customerDao.insertCustomer(newCustomer);
        System.out.println("Customer inserted: " + customerInserted);

        // skapa en ny mobil enhet för kunden
        // Antag att vi har kund-ID 1 för den nyligen införda kunden
        MobileDevice newDevice = new MobileDevice(0, 1, "BrandX", "ModelY", "SN123456", new Timestamp(System.currentTimeMillis()));
        boolean deviceInserted = mobileDeviceDao.insertMobileDevice(newDevice);
        System.out.println("Mobile device inserted: " + deviceInserted);


    }
}