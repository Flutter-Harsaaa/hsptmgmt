package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDAO {
    private final SessionFactory sessionFactory;

    public PaymentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public double totalRevenue() {
    	Session session = sessionFactory.openSession();
        Double result = session.createQuery("SELECT SUM(p.amount) FROM Payment p", Double.class)
                                     .getSingleResult();
        return result != null ? result : 0.0;
    }


    public Payment save(Payment payment) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(payment);
        tx.commit();
        s.close();
        return payment;
    }

    public Payment findById(Long id) {
        Session s = sessionFactory.openSession();
        Payment p = s.get(Payment.class, id);
        s.close();
        return p;
    }

    public List<Payment> findAll() {
        Session s = sessionFactory.openSession();
        List<Payment> list = s.createQuery("from Payment", Payment.class).list();
        s.close();
        return list;
    }

    public List<Payment> findPending() {
        Session s = sessionFactory.openSession();
        List<Payment> list = s.createQuery(
            "from Payment where status = 'Pending'", Payment.class).list();
        s.close();
        return list;
    }

    public void delete(Long id) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        Payment p = s.get(Payment.class, id);
        if (p != null) s.delete(p);
        tx.commit();
        s.close();
    }
}
