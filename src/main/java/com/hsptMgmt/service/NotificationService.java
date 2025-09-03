package com.hsptMgmt.service;

import com.hsptMgmt.dao.NotificationDAO;
import com.hsptMgmt.dao.PatientDAO;
import com.hsptMgmt.entity.Notification;
import com.hsptMgmt.entity.Patient;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.SessionFactory;

public class NotificationService {
    private final NotificationDAO notificationDAO;
	private PatientDAO patientDAO;

    public NotificationService(SessionFactory sessionFactory) {
        this.notificationDAO = new NotificationDAO(sessionFactory);
        this.patientDAO=new PatientDAO(sessionFactory);
    }

    public Notification queue(Notification n) { return notificationDAO.save(n); }

    public List<Notification> findPending() { return notificationDAO.findPending(); }

    public Notification markSent(Notification n) {
        n.setStatus("Sent");
        n.setSentTime(new Timestamp(System.currentTimeMillis()));
        return notificationDAO.save(n);
    }
    public Notification sendNotification(int patientId, String message) {
        Patient patient = patientDAO.findById(patientId);
        Notification notification = new Notification();
        notification.setPatient(patient);
        notification.setMessage(message);
        notificationDAO.save(notification);
        return notification;
    }



    public void flushPendingToConsole() {
        for (Notification n : notificationDAO.findPending()) {
            System.out.println("Sending to patientId=" + n.getPatientId() + " : " + n.getMessage());
            markSent(n);
        }
    }
}
