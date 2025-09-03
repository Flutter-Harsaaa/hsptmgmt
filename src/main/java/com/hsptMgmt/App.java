package com.hsptMgmt;

import com.hsptMgmt.entity.*;
import com.hsptMgmt.service.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class App {

    private static SessionFactory sessionFactory;

    private static PatientService patientService;
    private static DoctorService doctorService;
    private static AppointmentService appointmentService;
    private static PrescriptionService prescriptionService;
    private static MedicalHistoryService medicalHistoryService;
    private static PaymentService paymentService;
    private static NotificationService notificationService;

    public static void main(String[] args) {
        // Hibernate SessionFactory
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Initialize Services (DAO inside service)
        patientService = new PatientService(sessionFactory);
        doctorService = new DoctorService(sessionFactory);
        appointmentService = new AppointmentService(sessionFactory);
        prescriptionService = new PrescriptionService(sessionFactory);
        medicalHistoryService = new MedicalHistoryService(sessionFactory);
        paymentService = new PaymentService(sessionFactory);
        notificationService = new NotificationService(sessionFactory);
        

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
            case 1  -> addPatient(sc);
            case 2  -> addDoctor(sc);
            case 3  -> bookAppointment(sc);
            case 4  -> viewAllPatients();
            case 5  -> viewAllDoctors();
            case 6  -> approvePendingAppointment(sc);
            case 7  -> addPrescription(sc);
            case 8  -> viewMedicalHistory(sc);
            case 9  -> recordPayment(sc);
            case 10 -> sendNotification(sc);
            case 11 -> registerStaff(sc);
            case 12 -> allocateRoom(sc);
            case 13 -> dischargePatient(sc);
            case 14 -> cancelAppointment(sc);
            case 15 -> searchRecords(sc);
            case 16 -> viewAnalytics();
            case 0  -> System.out.println("Goodbye!");
            default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
        sessionFactory.close();
    }

    private static void printMenu() {
    	System.out.println("\n=== Hospital Management Console ===");
        System.out.println("1.  Add Patient");
        System.out.println("2.  Add Doctor");
        System.out.println("3.  Book Appointment Slot");
        System.out.println("4.  View All Patients");
        System.out.println("5.  View All Doctors");
        System.out.println("6.  Approve Pending Appointment");
        System.out.println("7.  Add Prescription");
        System.out.println("8.  View Medical History");
        System.out.println("9.  Record Payment");
        System.out.println("10. Send Notification");
        System.out.println("11. Register Hospital Staff");
        System.out.println("12. Allocate Room / Bed");
        System.out.println("13. Discharge Patient");
        System.out.println("14. Cancel Appointment & Refund");
        System.out.println("15. Search Patients/Doctors");
        System.out.println("16. View Hospital Analytics");
        System.out.println("0.  Exit");
        System.out.print("Enter choice: ");
    }

    // ======= Menu Methods =======

    private static void addPatient(Scanner sc) {
        System.out.print("Enter patient first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter patient second name: ");
        String secondName = sc.nextLine();
        System.out.print("Enter patient age: ");
        int age = sc.nextInt();
        System.out.print("Enter contact: ");
        String contact = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();

        Patient p = new Patient();
        p.setFirstName(firstName);
        p.setLastName(secondName);
        p.setContactNumber(contact);
        p.setAddress(address);
        p.setDob(age);

        Patient saved = patientService.registerPatient(p);

        // ✅ Display generated ID with patient name
        System.out.println("✅ Patient saved successfully!");
        System.out.println("🆔 Patient ID: " + saved.getPatientId());
        System.out.println("👤 Patient Name: " + saved.getFirstName() + " " + saved.getLastName());
    }


    private static void addDoctor(Scanner sc) {
        System.out.print("Enter doctor First name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter doctor Second name: ");
        String secondName = sc.nextLine();
        System.out.print("Enter contact number: ");
        String contact = sc.nextLine();
        System.out.print("Enter email id: ");
        String email = sc.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = sc.nextLine();

        Doctor doctor = new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(secondName);
        doctor.setContactNumber(contact);
        doctor.setSpecialization(specialization);
        doctor.setEmail(email);
        Doctor saved = doctorService.addDoctor(doctor);
        System.out.println("✅ Doctor saved succesfully ");
        System.out.println("👤 Doctor Name: " + saved.getFirstName() + " " + saved.getLastName()); 
        System.out.println("🆔 Doctor ID: " + saved.getDoctorId());
    
    }

    private static void bookAppointment(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        long did = sc.nextInt();

        Appointment appt = appointmentService.bookAppointment(pid, did, new Date(), "10:00 AM");
        if (appt != null) {
            System.out.println("✅ Appointment booked: ");
            System.out.println("🆔 Appointment ID: " + appt.getAppointmentId());
            System.out.println("Appointment With: " + appt.getDoctorId());
        } else {
            System.out.println("❌ Booking failed.");
        }
    }

    private static void viewAllPatients() {
        List<Patient> patients = patientService.getAllPatients();

        if (patients == null || patients.isEmpty()) {
            System.out.println("⚠️ No patients found!");
            return;
        }

        System.out.println("📋 List of Patients:");
        for (Patient p : patients) {
            System.out.println("🆔 " + p.getPatientId() +
                               " | 👤 " + p.getFirstName() + " " + p.getLastName() +
                               " | 📞 " + p.getContactNumber());
        }
    }


    private static void viewAllDoctors() {
       List<Doctor> doctor=doctorService.getAllDoctors();
       if (doctor == null || doctor.isEmpty()) {
           System.out.println("⚠️ No patients found!");
           return;
       }

       System.out.println("📋 List of Patients:");
       for (Doctor p : doctor) {
           System.out.println("🆔 " + p.getDoctorId() +
                              " | 👤 " + p.getFirstName() + " " + p.getLastName() +
                              " | 📞 " + p.getContactNumber()+" | Department :"+p.getSpecialization());
       }
    }

    private static void approvePendingAppointment(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        long id = sc.nextInt();
        Appointment appt = appointmentService.approveAppointment(id);
        System.out.println(appt != null ? "✅ Approved: " + appt : "❌ Not found.");
    }

    private static void addPrescription(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter diagnosis: ");
        String diagnosis = sc.nextLine();
        System.out.print("Enter medicines: ");
        String medicines = sc.nextLine();

        System.out.print("Enter prescription instructions: ");
        String instructions = sc.nextLine();

        Prescription pres = prescriptionService.addPrescription(appointmentId, medicines, instructions,diagnosis);
        System.out.println("✅ Prescription saved with ID: " + pres.getPrescriptionId());
    }


    private static void viewMedicalHistory(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        Long pid = sc.nextLong();

        List<MedicalHistory> histories = medicalHistoryService.getHistoryByPatient(pid);

        if (histories.isEmpty()) {
            System.out.println("❌ No medical history found for Patient ID: " + pid);
        } else {
            System.out.println("📜 Medical History for Patient ID " + pid + ":");
            histories.forEach(history -> {
                System.out.println("--------------------------------------------------");
                System.out.println("History ID   : " + history.getHistoryId());
                System.out.println("Diagnosis    : " + history.getDiagnosis());
                System.out.println("Treatment    : " + history.getTreatment());
                System.out.println("Date         : " + history.getDate());
                System.out.println("Notes        : " + history.getNotes());
            });
        }
    }


    private static void recordPayment(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        int aid = sc.nextInt();
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();
        Payment payment = new Payment();
        payment.setPatient(patientService.getPatient(aid));
//        payment.se;
        payment.setAmount(amt);
        Payment pay = paymentService.recordPayment(payment);
        System.out.println("✅ Payment recorded for : " + pay.getAppointment());
    }

    private static void sendNotification(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter message: ");
        String msg = sc.nextLine();

        Notification notif = notificationService.sendNotification(pid, msg);
        System.out.println("✅ Notification sent: " + notif);
    }
    
    private static void registerStaff(Scanner sc) {
        System.out.print("Enter Staff First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Staff First Name: ");
        String secondName = sc.nextLine();
        System.out.print("Enter Role (Doctor/Nurse/Receptionist/Admin): ");
        String role = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        HospitalStaff staff = new HospitalStaff();
        staff.setFirstName(firstName);
        staff.setLastName(secondName);
        staff.setRole(role);
        staff.setContactNumber(contact);
        staff.setEmail(email);

        HospitalStaff saved = new HospitalStaff();
        System.out.println("✅ Staff Registered with ID: " + saved.getStaffId());
    }
    private static RoomService roomService = new RoomService(sessionFactory);

    
    private static void allocateRoom(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        Long pid = sc.nextLong(); sc.nextLine();
        System.out.print("Enter Room Type (ICU/General/Private): ");
        Long type = sc.nextLong();

        Room room = roomService.allocateRoom(pid, type);
        System.out.println("✅ Room allocated: " + room.getRoomId() + " to Patient ID " + pid);
    }

    private static void dischargePatient(Scanner sc) {
        System.out.print("Enter Room ID: ");
        Long rid = sc.nextLong(); sc.nextLine();

        roomService.dischargePatient(rid);
        System.out.println("✅ Patient discharged and Room freed.");
    }

    private static void cancelAppointment(Scanner sc) {
        System.out.print("Enter Appointment ID to Cancel: ");
        Long aid = sc.nextLong(); sc.nextLine();

        boolean refunded = appointmentService.cancelAndRefund(aid);
        if (refunded)
            System.out.println("✅ Appointment cancelled and payment refunded.");
        else
            System.out.println("⚠️ Could not cancel. Check ID.");
    }

    private static void searchRecords(Scanner sc) {
        System.out.println("Search by: 1. Patient Name  2. Doctor Specialization");
        int choice = sc.nextInt(); sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter Patient Name: ");
            String name = sc.nextLine();
            patientService.searchByName(name).forEach(System.out::println);
        } else {
            System.out.print("Enter Specialization: ");
            String spec = sc.nextLine();
            doctorService.searchBySpecialization(spec).forEach(System.out::println);
        }
    }

    private static void viewAnalytics() {
        System.out.println("\n=== Hospital Analytics ===");
        System.out.println("Total Patients: " + patientService.countPatients());
        System.out.println("Total Doctors: " + doctorService.countDoctors());
        System.out.println("Total Appointments: " + appointmentService.countAppointments());
        System.out.println("Total Revenue Collected: ₹" + paymentService.totalRevenue());
    }

}
