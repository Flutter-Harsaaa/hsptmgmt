package com.hsptMgmt.dao;

import com.hsptMgmt.entity.MedicalHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MedicalHistoryDAO {
    private final SessionFactory sessionFactory;

    public MedicalHistoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MedicalHistory save(MedicalHistory history) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(history);
        tx.commit();
        s.close();
        return history;
    }

    public MedicalHistory findById(Long id) {
        Session s = sessionFactory.openSession();
        MedicalHistory mh = s.get(MedicalHistory.class, id);
        s.close();
        return mh;
    }

    public List<MedicalHistory> findAll() {
        Session s = sessionFactory.openSession();
        List<MedicalHistory> list = s.createQuery("from MedicalHistory", MedicalHistory.class).list();
        s.close();
        return list;
    }

    public List<MedicalHistory> findByPatientId(Long patientId) {
        Session s = sessionFactory.openSession();
        List<MedicalHistory> list = s.createQuery(
            "from MedicalHistory where patient.patientId = :pid", MedicalHistory.class)
            .setParameter("pid", patientId)
            .list();
        s.close();
        return list;
    }

    public void delete(Long id) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        MedicalHistory mh = s.get(MedicalHistory.class, id);
        if (mh != null) s.delete(mh);
        tx.commit();
        s.close();
    }
}
