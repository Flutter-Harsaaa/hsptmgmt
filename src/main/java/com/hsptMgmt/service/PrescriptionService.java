package com.hsptMgmt.service;

import com.hsptMgmt.dao.AppointmentDAO;
import com.hsptMgmt.dao.PrescriptionDAO;
import com.hsptMgmt.entity.Appointment;
import com.hsptMgmt.entity.Prescription;

import java.util.List;

import org.hibernate.SessionFactory;

public class PrescriptionService {
    private final PrescriptionDAO prescriptionDAO;
	private final  AppointmentDAO appointmentDAO;

    public PrescriptionService(SessionFactory sessionFactory) {
        this.prescriptionDAO = new PrescriptionDAO(sessionFactory);
        this.appointmentDAO = new AppointmentDAO(sessionFactory);
    }

 // PrescriptionService.java
    public Prescription addPrescription(int appointmentId, String medicines, String instructions, String diagnosis) {
        Appointment appointment = appointmentDAO.findById(appointmentId);
        if (appointment == null) {
            throw new IllegalArgumentException("❌ Appointment not found with ID: " + appointmentId);
        }

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setDoctor(appointment.getDoctor());   // from appointment
        prescription.setPatient(appointment.getPatient()); // from appointment
        prescription.setDate(new java.sql.Date(System.currentTimeMillis()));
        prescription.setMedicines(medicines);
        prescription.setInstructions(instructions);
        prescription.setDiagnosis(instructions);

        return prescriptionDAO.save(prescription);
    }




    public Prescription getPrescription(Long id) { return prescriptionDAO.findById(id); }

    public List<Prescription> getAllPrescriptions() { return prescriptionDAO.findAll(); }

    public List<Prescription> getPrescriptionsByPatient(Long patientId) {
        return prescriptionDAO.findByPatientId(patientId);
    }

    public void deletePrescription(Long id) { prescriptionDAO.delete(id); }
}
