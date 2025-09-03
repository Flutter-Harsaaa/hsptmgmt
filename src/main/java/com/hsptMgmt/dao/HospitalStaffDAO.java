package com.hsptMgmt.dao;

import com.hsptMgmt.entity.HospitalStaff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HospitalStaffDAO {
    private final SessionFactory sessionFactory;

    public HospitalStaffDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    

    public HospitalStaff save(HospitalStaff staff) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(staff);
        tx.commit();
        s.close();
        return staff;
    }

    public HospitalStaff findById(Long id) {
        Session s = sessionFactory.openSession();
        HospitalStaff staff = s.get(HospitalStaff.class, id);
        s.close();
        return staff;
    }

    public List<HospitalStaff> findAll() {
        Session s = sessionFactory.openSession();
        List<HospitalStaff> list = s.createQuery("from HospitalStaff", HospitalStaff.class).list();
        s.close();
        return list;
    }

    public void delete(Long id) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        HospitalStaff staff = s.get(HospitalStaff.class, id);
        if (staff != null) s.delete(staff);
        tx.commit();
        s.close();
    }
}
