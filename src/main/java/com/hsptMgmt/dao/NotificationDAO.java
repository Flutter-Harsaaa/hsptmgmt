package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class NotificationDAO {
    private final SessionFactory sessionFactory;

    public NotificationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Notification save(Notification notification) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(notification);
        tx.commit();
        s.close();
        return notification;
    }

    public Notification findById(Long id) {
        Session s = sessionFactory.openSession();
        Notification n = s.get(Notification.class, id);
        s.close();
        return n;
    }

    public List<Notification> findAll() {
        Session s = sessionFactory.openSession();
        List<Notification> list = s.createQuery("from Notification", Notification.class).list();
        s.close();
        return list;
    }

    public List<Notification> findPending() {
        Session s = sessionFactory.openSession();
        List<Notification> list = s.createQuery(
            "from Notification where status = 'Pending'", Notification.class).list();
        s.close();
        return list;
    }

    public void delete(Long id) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        Notification n = s.get(Notification.class, id);
        if (n != null) s.delete(n);
        tx.commit();
        s.close();
    }
}
