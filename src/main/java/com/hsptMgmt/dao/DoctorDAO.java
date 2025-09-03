// com/hsptMgmt/dao/DoctorDAO.java
package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DoctorDAO {
    private SessionFactory sessionFactory;

    public DoctorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public long count() {
        Session session = sessionFactory.openSession();
        Long cnt = session.createQuery("SELECT COUNT(a) FROM Appointment a", Long.class).uniqueResult();
        session.close();
        return cnt != null ? cnt : 0L;
    }


    public Doctor save(Doctor doctor) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(doctor);
        tx.commit();
        session.close();
        return doctor;
    }

    public Doctor findById(long doctorId) {
        Session session = sessionFactory.openSession();
        Doctor doctor = session.get(Doctor.class, doctorId);
        session.close();
        return doctor;
    }

    public List<Doctor> findAll() {
        Session session = sessionFactory.openSession();
        List<Doctor> doctors = session.createQuery("from Doctor", Doctor.class).list();
        session.close();
        return doctors;
    }
    public List<Doctor> findBySpecialization(String specialization) {
    	Session session = sessionFactory.openSession();
        return session.createQuery("FROM Doctor d WHERE d.specialization = :spec", Doctor.class)
                            .setParameter("spec", specialization)
                            .getResultList();
    }


    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Doctor doctor = session.get(Doctor.class, id);
        if (doctor != null) {
            session.delete(doctor);
        }
        tx.commit();
        session.close();
    }
}
