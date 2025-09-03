// com/hsptMgmt/dao/SlotDAO.java
package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Slot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SlotDAO {
    private SessionFactory sessionFactory;

    public SlotDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Slot save(Slot slot) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(slot);
        tx.commit();
        session.close();
        return slot;
    }

    public Slot findById(int id) {
        Session session = sessionFactory.openSession();
        Slot slot = session.get(Slot.class, id);
        session.close();
        return slot;
    }

    public List<Slot> findAll() {
        Session session = sessionFactory.openSession();
        List<Slot> list = session.createQuery("from Slot", Slot.class).list();
        session.close();
        return list;
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Slot slot = session.get(Slot.class, id);
        if (slot != null) {
            session.delete(slot);
        }
        tx.commit();
        session.close();
    }
}
