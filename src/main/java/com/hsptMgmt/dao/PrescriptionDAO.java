package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PrescriptionDAO {
    private final SessionFactory sessionFactory;

    public PrescriptionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Prescription save(Prescription prescription) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(prescription);
        tx.commit();
        s.close();
        return prescription;
    }

    public Prescription findById(Long id) {
        Session s = sessionFactory.openSession();
        Prescription p = s.get(Prescription.class, id);
        s.close();
        return p;
    }

    public List<Prescription> findAll() {
        Session s = sessionFactory.openSession();
        List<Prescription> list = s.createQuery("from Prescription", Prescription.class).list();
        s.close();
        return list;
    }

    public List<Prescription> findByPatientId(Long patientId) {
        Session s = sessionFactory.openSession();
        List<Prescription> list = s.createQuery(
            "from Prescription where patient.patientId = :pid", Prescription.class)
            .setParameter("pid", patientId)
            .list();
        s.close();
        return list;
    }

    public void delete(Long id) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        Prescription p = s.get(Prescription.class, id);
        if (p != null) s.delete(p);
        tx.commit();
        s.close();
    }
}
