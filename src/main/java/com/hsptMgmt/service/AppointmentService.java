 package com.hsptMgmt.service;

import com.hsptMgmt.dao.AppointmentDAO;
import com.hsptMgmt.dao.DoctorDAO;
import com.hsptMgmt.dao.PatientDAO;
import com.hsptMgmt.entity.Appointment;
import com.hsptMgmt.entity.Doctor;
import com.hsptMgmt.entity.Patient;

import java.util.Date;
import java.util.List;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;
	private PatientDAO patientDAO;
	private DoctorDAO doctorDAO;

    public AppointmentService(org.hibernate.SessionFactory sessionFactory) {
        this.appointmentDAO = new AppointmentDAO(sessionFactory);
        this.patientDAO =new PatientDAO(sessionFactory);
        this.doctorDAO= new DoctorDAO(sessionFactory);
    }
    
    public boolean cancelAndRefund(Long appointmentId) {
        Appointment appt = appointmentDAO.findById(appointmentId);
        if (appt == null) return false;

        // cancel appointment
        appointmentDAO.delete(appt.getAppointmentId());

//        // refund payment
//        if (appt.getPayment() != null) {
//            refundService.processRefund(appt.getPayment());
//        }
        return true;
    }

    public long countAppointments() {
        return appointmentDAO.count();
    }


    public Appointment bookAppointment(Patient patient, Doctor doctor, Date date, String time) {
        Long slotCount = appointmentDAO.countByDoctorAndDate(doctor.getDoctorId(), date);

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(date);
        appointment.setTime(time);

        // First 15 slots auto-confirmed
        appointment.setStatus(slotCount < 15 ? "CONFIRMED" : "PENDING");
        

        return appointmentDAO.save(appointment);
    }

    // ✅ Overloaded method (works with IDs, for App.java)
    public Appointment bookAppointment(int patientId, long doctorId, Date date, String time) {
        Patient patient = patientDAO.findById(patientId);
        Doctor doctor = doctorDAO.findById(doctorId);

        return bookAppointment(patient, doctor, date, time);
    }

    public Appointment approveAppointment(long id) {
        Appointment appointment = appointmentDAO.findById(id);
        if (appointment != null && "PENDING".equals(appointment.getStatus())) {
            appointment.setStatus("CONFIRMED");
            return appointmentDAO.update(appointment);
        }
        return appointment;
    }

    public List<Appointment> findAll() {
        return appointmentDAO.findAll();
    }

    public Appointment findById(int id) {
        return appointmentDAO.findById(id);
    }
}
