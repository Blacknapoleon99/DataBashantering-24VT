package com.napoleon.application;

import com.napoleon.dao.CustomerDAO;
import com.napoleon.dao.MobileDeviceDAO;
import com.napoleon.dao.RepairJobDAO;
import com.napoleon.model.Customer;
import com.napoleon.model.MobileDevice;
import com.napoleon.model.RepairJob;
import java.sql.SQLException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;



public class Main {
    private static final CustomerDAO customerDao = new CustomerDAO();
    private static final MobileDeviceDAO mobileDeviceDao = new MobileDeviceDAO();
    private static final RepairJobDAO repairJobDao = new RepairJobDAO();

    private static final Scanner scanner = new Scanner(System.in);
    private static Timestamp estimatedCompletionDate;


    public static void main(String[] args) {
        System.out.println("Välkommen till Napoleons Hanteringssystem");
        boolean running = true;
        while (running) {
            System.out.println("\nHuvudmeny:");
            System.out.println("1. Hantera kunder");
            System.out.println("2. Hantera mobila enheter");
            System.out.println("3. Hantera reparationsjobb");
            System.out.println("4. Avsluta programmet");
            System.out.print("Välj ett alternativ: ");

            int choice = Integer.parseInt(scanner.nextLine()); // nextLine för att undvika input-fel

            switch (choice) {
                case 1:
                    manageCustomers();
                    break;
                case 2:
                    manageMobileDevices();
                    break;
                case 3:
                    manageRepairJobs();
                    break;
                case 4:
                    System.out.println("Avslutar programmet...");
                    running = false;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void manageCustomers() {
        boolean back = false;
        while (!back) {
            System.out.println("\nHantera kunder:");
            System.out.println("1. Lägg till kund");
            System.out.println("2. Visa alla kunder");
            System.out.println("3. Uppdatera kund");
            System.out.println("4. Ta bort kund");
            System.out.println("5. Återgå till huvudmenyn");
            System.out.print("Välj ett alternativ: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    listCustomers();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void addCustomer() {
        System.out.println("\nLägg till ny kund");
        System.out.print("Ange namn: ");
        String name = scanner.nextLine();
        System.out.print("Ange email: ");
        String email = scanner.nextLine();
        System.out.print("Ange telefon: ");
        String phone = scanner.nextLine();
        System.out.print("Ange adress: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, email, phone, address);
        try {
            int id = customerDao.insertCustomer(customer);
            System.out.println("Kund med ID " + id + " tillagd.");
        } catch (SQLException e) {
            System.out.println("Kunde inte lägga till kund: " + e.getMessage());
        }
    }

    private static void listCustomers() {
        try {
            List<Customer> customers = customerDao.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("Inga kunder att visa.");
            } else {
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
            }
        } catch (SQLException e) {
            System.out.println("Kunde inte hämta kunder: " + e.getMessage());
        }
    }

    private static void updateCustomer() {
        System.out.println("\nUppdatera kund");
        System.out.print("Ange kundens ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());

        Customer existingCustomer = customerDao.getCustomerById(customerId);
        if (existingCustomer == null) {
            System.out.println("Kunden med ID " + customerId + " finns inte.");
            return;
        }

        System.out.println("Nuvarande uppgifter: " + existingCustomer);
        System.out.println("Lämna fältet tomt om du inte vill ändra värdet.");

        System.out.print("Nytt namn (" + existingCustomer.getName() + "): ");
        String name = scanner.nextLine();
        name = name.isEmpty() ? existingCustomer.getName() : name;

        System.out.print("Ny email (" + existingCustomer.getEmail() + "): ");
        String email = scanner.nextLine();
        email = email.isEmpty() ? existingCustomer.getEmail() : email;

        System.out.print("Nytt telefonnummer (" + existingCustomer.getPhone() + "): ");
        String phone = scanner.nextLine();
        phone = phone.isEmpty() ? existingCustomer.getPhone() : phone;

        System.out.print("Ny adress (" + existingCustomer.getAddress() + "): ");
        String address = scanner.nextLine();
        address = address.isEmpty() ? existingCustomer.getAddress() : address;

        Customer updatedCustomer = new Customer(customerId, name, email, phone, address);
        boolean success = customerDao.updateCustomer(updatedCustomer);
        if (success) {
            System.out.println("Kunden med ID " + customerId + " har uppdaterats.");
        } else {
            System.out.println("Kunde inte uppdatera kunden.");
        }
    }


    private static void deleteCustomer() {
        System.out.print("Ange kundens ID för att ta bort: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        boolean success = customerDao.deleteCustomer(customerId);
        if (success) {
            System.out.println("Kund med ID " + customerId + " har tagits bort.");
        } else {
            System.out.println("Kunde inte ta bort kund med ID " + customerId);
        }
    }
    private static void addMobileDevice() {
        System.out.println("\nLägg till en ny mobil enhet");
        try {
            System.out.print("Ange kundens ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            System.out.print("Ange märke: ");
            String brand = scanner.nextLine();

            System.out.print("Ange modell: ");
            String model = scanner.nextLine();

            System.out.print("Ange serienummer: ");
            String serialNumber = scanner.nextLine();

            System.out.print("Ange inlämningsdatum (YYYY-MM-DD): ");
            String submissionDateStr = scanner.nextLine();
            Timestamp submissionDate = Timestamp.valueOf(submissionDateStr + " 00:00:00");

            MobileDevice device = new MobileDevice(0, customerId, brand, model, serialNumber, submissionDate); // Antag att ID är auto-genererat
            int deviceId = mobileDeviceDao.insertMobileDevice(device);

            if (deviceId > 0) {
                System.out.println("En ny mobil enhet har lagts till med enhets-ID: " + deviceId);
            } else {
                System.out.println("Det gick inte att lägga till den mobila enheten.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Felaktig inmatning, var vänlig och ange numeriska värden där det efterfrågas.");
        } catch (Exception e) {
            System.out.println("Ett fel uppstod vid tillägg av den mobila enheten: " + e.getMessage());
        }
    }

    private static void listMobileDevices() {
        try {
            List<MobileDevice> devices = mobileDeviceDao.getAllMobileDevices();
            if (devices.isEmpty()) {
                System.out.println("Inga mobila enheter finns registrerade.");
            } else {
                for (MobileDevice device : devices) {
                    System.out.println(device);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ett fel uppstod vid hämtning av mobila enheter: " + e.getMessage());
        }
    }






    private static void manageMobileDevices() {
        boolean back = false;
        while (!back) {
            System.out.println("\nHantera mobila enheter:");
            System.out.println("1. Lägg till ny mobil enhet");
            System.out.println("2. Visa alla mobila enheter");
            System.out.println("3. Uppdatera mobil enhet");
            System.out.println("4. Ta bort mobil enhet");
            System.out.println("5. Återgå till huvudmenyn");
            System.out.print("Välj ett alternativ: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Rensa buffer

            switch (choice) {
                case 1:
                    addMobileDevice();
                    break;
                case 2:
                    listMobileDevices();
                    break;
                case 3:
                    updateMobileDevice();
                    break;
                case 4:
                    deleteMobileDevice();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void updateMobileDevice() {
        try {
            System.out.println("\nUppdatera en mobil enhet");
            System.out.print("Ange ID för den mobila enheten du vill uppdatera: ");
            int deviceId = Integer.parseInt(scanner.nextLine());

            if (!mobileDeviceDao.deviceExists(deviceId)) {
                System.out.println("Ingen mobil enhet med det angivna ID:t finns.");
                return;
            }

            System.out.print("Ange nytt märke (lämna tomt för att inte ändra): ");
            String brand = scanner.nextLine();

            System.out.print("Ange ny modell (lämna tomt för att inte ändra): ");
            String model = scanner.nextLine();

            System.out.print("Ange nytt serienummer (lämna tomt för att inte ändra): ");
            String serialNumber = scanner.nextLine();

            MobileDevice deviceToUpdate = mobileDeviceDao.getMobileDeviceById(deviceId);
            if (!brand.isEmpty()) deviceToUpdate.setBrand(brand);
            if (!model.isEmpty()) deviceToUpdate.setModel(model);
            if (!serialNumber.isEmpty()) deviceToUpdate.setSerialNumber(serialNumber);

            boolean success = mobileDeviceDao.updateMobileDevice(deviceToUpdate);

            if (success) {
                System.out.println("Mobil enhet har uppdaterats.");
            } else {
                System.out.println("Det gick inte att uppdatera den mobila enheten.");
            }
        } catch (Exception e) {
            System.out.println("Ett fel uppstod vid uppdatering av den mobila enheten: " + e.getMessage());
        }
    }



    private static void manageRepairJobs() {
        boolean back = false;
        while (!back) {
            System.out.println("\nHantera reparationsjobb:");
            System.out.println("1. Lägg till nytt reparationsjobb");
            System.out.println("2. Visa alla reparationsjobb");
            System.out.println("3. Uppdatera reparationsjobb");
            System.out.println("4. Ta bort reparationsjobb");
            System.out.println("5. Återgå till huvudmenyn");
            System.out.print("Välj ett alternativ: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addRepairJob();
                    break;
                case 2:
                    listRepairJobs();
                    break;
                case 3:
                    updateRepairJob();
                    break;
                case 4:
                    deleteRepairJob();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    // Ska implementera input-validering och felhantering för varje metod.

    private static void deleteMobileDevice() {
        System.out.print("Ange ID för den mobila enheten du vill ta bort: ");
        int deviceId = Integer.parseInt(scanner.nextLine());

        try {
            if (mobileDeviceDao.deviceExists(deviceId)) {
                boolean success = mobileDeviceDao.deleteMobileDevice(deviceId);
                if (success) {
                    System.out.println("Den mobila enheten har tagits bort.");
                } else {
                    System.out.println("Det gick inte att ta bort den mobila enheten.");
                }
            } else {
                System.out.println("Ingen mobil enhet med det angivna ID:t finns.");
            }
        } catch (SQLException e) {
            System.out.println("Ett fel inträffade: " + e.getMessage());
        }
    }

    private static void addRepairJob() {
        try {
            System.out.println("\nLägg till ett nytt reparationsjobb");

            System.out.print("Ange enhetens ID: ");
            int deviceId = Integer.parseInt(scanner.nextLine());

            if (!mobileDeviceDao.deviceExists(deviceId)) {
                System.out.println("Enheten med angivet ID finns inte.");
                return;
            }

            System.out.print("Ange problem beskrivning: ");
            String problemDescription = scanner.nextLine();

            System.out.print("Ange status för reparationen (t.ex. 'received', 'in_progress', 'completed', 'delivered'): ");
            String status = scanner.nextLine().toLowerCase(); // Normalisera input till lowercase
            switch (status) {
                case "received":
                case "in_progress":
                case "completed":
                case "delivered":
                    break; // giltiga värden
                default:
                    System.out.println("Ogiltig status. Använd endast 'received', 'in_progress', 'completed', 'delivered'.");
                    return;
            }

            System.out.print("Ange beräknat färdigdatum och tid (YYYY-MM-DD HH:MM): ");
            String dateTimeStr = scanner.nextLine();
            Timestamp estimatedCompletionDate = null;
            if (!dateTimeStr.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date parsedDate = dateFormat.parse(dateTimeStr);
                    estimatedCompletionDate = new Timestamp(parsedDate.getTime());
                } catch (ParseException e) {
                    System.out.println("Felaktigt datumformat. Ange datumet i formatet YYYY-MM-DD HH:MM");
                    return; // Avbryter metoden om datumet är i fel format
                }
            }

            // Skapa ett nytt RepairJob-objekt med estimatedCompletionDate
            RepairJob newJob = new RepairJob(0, deviceId, problemDescription, status, estimatedCompletionDate, null, "");
            boolean success = repairJobDao.insertRepairJob(newJob);

            // ... resten av din kod ...
        } catch (Exception e) {
            System.out.println("Ett fel uppstod vid tillägg av reparationsjobbet: " + e.getMessage());
        }
    }


    private static void deleteRepairJob() {
        try {
            System.out.println("\nTa bort ett reparationsjobb");
            System.out.print("Ange ID för reparationsjobbet du vill ta bort: ");
            int jobId = Integer.parseInt(scanner.nextLine());

            boolean success = repairJobDao.deleteRepairJob(jobId);

            if (success) {
                System.out.println("Reparationsjobbet har tagits bort.");
            } else {
                System.out.println("Det gick inte att ta bort reparationsjobbet.");
            }
        } catch (Exception e) {
            System.out.println("Ett fel uppstod vid borttagning av reparationsjobbet: " + e.getMessage());
        }
    }

    private static void updateRepairJob() {
        try {
            System.out.println("\nUppdatera ett reparationsjobb");
            System.out.print("Ange ID för reparationsjobbet du vill uppdatera: ");
            int jobId = Integer.parseInt(scanner.nextLine());

            RepairJob jobToUpdate = repairJobDao.getRepairJobById(jobId);
            if (jobToUpdate == null) {
                System.out.println("Reparationsjobbet finns inte.");
                return;
            }
            System.out.print("Ange nytt beräknat färdigdatum och tid (YYYY-MM-DD HH:MM), lämna tomt om inte ändring: ");
            String newDateTimeStr = scanner.nextLine();
            if (!newDateTimeStr.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date parsedDate = dateFormat.parse(newDateTimeStr);
                    Timestamp newEstimatedCompletionDate = new Timestamp(parsedDate.getTime());
                    jobToUpdate.setEstimatedCompletionDate(newEstimatedCompletionDate);
                } catch (ParseException e) {
                    System.out.println("Felaktigt datumformat. Ange datumet i formatet YYYY-MM-DD HH:MM");
                    return; // Avbryter metoden om datumet är i fel format
                }
            }

            System.out.print("Ange ny problem beskrivning (lämna tomt för att inte ändra): ");
            String problemDescription = scanner.nextLine();
            if (!problemDescription.isEmpty()) jobToUpdate.setProblemDescription(problemDescription);

            System.out.print("Ange ny status (lämna tomt för att inte ändra): ");
            String status = scanner.nextLine();
            if (!status.isEmpty()) jobToUpdate.setRepairStatus(status);

            boolean success = repairJobDao.updateRepairJob(jobToUpdate);

            if (success) {
                System.out.println("Reparationsjobbet har uppdaterats.");
            } else {
                System.out.println("Det gick inte att uppdatera reparationsjobbet.");
            }
        } catch (Exception e) {
            System.out.println("Ett fel uppstod vid uppdatering av reparationsjobbet: " + e.getMessage());
        }

    }

    private static void listRepairJobs() {
        try {
            List<RepairJob> repairJobs = repairJobDao.getAllRepairJobs();
            if (repairJobs.isEmpty()) {
                System.out.println("Det finns inga registrerade reparationsjobb.");
            } else {
                System.out.println("\nLista över alla reparationsjobb:");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // Formatering för datum och tid
                for (RepairJob job : repairJobs) {
                    String formattedEstimatedCompletionDate = job.getEstimatedCompletionDate() != null ?
                            dateFormat.format(job.getEstimatedCompletionDate()) : "N/A";
                    System.out.println("Job ID: " + job.getJobId() +
                            ", Device ID: " + job.getDeviceId() +
                            ", Problem: " + job.getProblemDescription() +
                            ", Repair Status: " + job.getRepairStatus() +
                            ", Estimated Completion Date: " + formattedEstimatedCompletionDate); // Använder den formaterade strängen
                }
            }
        } catch (SQLException e) {
            System.out.println("Ett fel uppstod när reparationsjobben skulle listas: " + e.getMessage());
        }
    }


    private static int getValidatedIntegerInput(String prompt) {
        int input = -1;
        while (input < 0) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                if (input < 0) {
                    System.out.println("Ange ett positivt tal.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ogiltig inmatning, ange ett nummer.");
            }
        }
        return input;
    }


}

