
package com.hsptMgmt.entity;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Column(columnDefinition = "TEXT")
    private String message;

    private Date createdAt;
    private Timestamp sentTime;
    private String status; // Pending, Sent, Failed

    public Notification() {}

    // Convenience ID helpers for services
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

    // Getters and Setters
    public Long getNotificationId() { return notificationId; }
    public void setNotificationId(Long notificationId) { this.notificationId = notificationId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date date) { this.createdAt = date; }

    public Timestamp getSentTime() { return sentTime; }
    public void setSentTime(Timestamp sentTime) { this.sentTime = sentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
