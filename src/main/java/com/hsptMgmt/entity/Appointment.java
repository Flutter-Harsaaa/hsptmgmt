
package com.hsptMgmt.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;

    // Duplicate date/time for quick queries (and to match existing services)
    private java.util.Date date;

    private String time;

    @Column(nullable = false)
    private String status; // AutoConfirmed, PendingApproval, Approved, Rejected, Completed

    @ManyToOne
    @JoinColumn(name = "approval_by")
    private HospitalStaff approvalBy; // Null if auto-confirmed

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Prescription prescription;

    public Appointment() {}

    // Convenience ID helpers used by services
    @Transient
    public Long getPatientId() {
        return patient != null ? patient.getPatientId() : null;
    }
    public void setPatientId(Long patientId) {
        if (patientId == null) { this.patient = null; }
        else {
            if (this.patient == null) this.patient = new Patient();
            this.patient.setPatientId(patientId);
        }
    }

    @Transient
    public Long getDoctorId() {
        return doctor != null ? doctor.getDoctorId() : null;
    }
    public void setDoctorId(Long doctorId) {
        if (doctorId == null) { this.doctor = null; }
        else {
            if (this.doctor == null) this.doctor = new Doctor();
            this.doctor.setDoctorId(doctorId);
        }
    }

    // Getters and Setters
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Slot getSlot() { return slot; }
    public void setSlot(Slot slot) { this.slot = slot; }

    public java.util.Date getDate() { return date; }
    public void setDate(java.util.Date date2) { this.date = date2; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public HospitalStaff getApprovalBy() { return approvalBy; }
    public void setApprovalBy(HospitalStaff approvalBy) { this.approvalBy = approvalBy; }

    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { this.prescription = prescription; }
}
