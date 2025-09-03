// com/hsptMgmt/dao/AppointmentDAO.java
package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AppointmentDAO {
    private SessionFactory sessionFactory;

    public AppointmentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public long count() {
        Session session = sessionFactory.openSession();
        Long cnt = session.createQuery("SELECT COUNT(a) FROM Appointment a", Long.class).uniqueResult();
        session.close();
        return cnt != null ? cnt : 0L;
    }


    public Appointment save(Appointment appointment) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(appointment);
        tx.commit();
        session.close();
        return appointment;
    }
    
    public Long countByDoctorAndDate(Long doctorId, java.util.Date date) {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery(
            "SELECT COUNT(a) FROM Appointment a WHERE a.doctor.id = :doctorId AND a.date = :date", Long.class)
            .setParameter("doctorId", doctorId)
            .setParameter("date", date)
            .uniqueResult();
        session.close();
        return count;
    }


    public Appointment findById(long id) {
        Session session = sessionFactory.openSession();
        Appointment appointment = session.get(Appointment.class, id);
        session.close();
        return appointment;
    }

    public List<Appointment> findByDoctorId(long doctorId) {
        Session session = sessionFactory.openSession();
        List<Appointment> appointments = session.createQuery(
            "from Appointment where doctor.id = :docId", Appointment.class)
            .setParameter("docId", doctorId)
            .list();
        session.close();
        return appointments;
    }

    public void delete(Long long1) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Appointment appointment = session.get(Appointment.class, long1);
        if (appointment != null) {
            session.delete(appointment);
        }
        tx.commit();
        session.close();
    }
    public List<Appointment> findAll() {
        Session session = sessionFactory.openSession();
        List<Appointment> list = session.createQuery("from Appointment", Appointment.class).list();
        session.close();
        return list;
    }
    


    public Appointment update(Appointment appointment) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(appointment);
        tx.commit();
        session.close();
        return appointment;
    }

}
