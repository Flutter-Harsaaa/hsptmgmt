// com/hsptMgmt/dao/PatientDAO.java
package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PatientDAO {
    private SessionFactory sessionFactory;

    public PatientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Patient> findByName(String name) {
    	Session session = sessionFactory.openSession();
        return session.createQuery("FROM Patient p WHERE p.name LIKE :name", Patient.class)
                            .setParameter("name", "%" + name + "%")
                            .getResultList();
    }
    
    public long count() {
        Session session = sessionFactory.openSession();
        Long cnt = session.createQuery("SELECT COUNT(a) FROM Appointment a", Long.class).uniqueResult();
        session.close();
        return cnt != null ? cnt : 0L;
    }


    public Patient save(Patient patient) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(patient);
        tx.commit();
        session.close();
        return patient;
    }

    public  Patient findById(long id) {
        Session session = sessionFactory.openSession();
        Patient patient = session.get(Patient.class, id);
        session.close();
        return patient;
    }

    public List<Patient> findAll() {
        Session session = sessionFactory.openSession();
        List<Patient> patients = session.createQuery("from Patient", Patient.class).list();
        session.close();
        return patients;
    }

    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Patient patient = session.get(Patient.class, id);
        if (patient != null) {
            session.delete(patient);
        }
        tx.commit();
        session.close();
    }
}
