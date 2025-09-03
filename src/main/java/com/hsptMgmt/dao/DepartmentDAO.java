// com/hsptMgmt/dao/DepartmentDAO.java
package com.hsptMgmt.dao;

import com.hsptMgmt.entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DepartmentDAO {
    private SessionFactory sessionFactory;

    public DepartmentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Department save(Department department) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(department);
        tx.commit();
        session.close();
        return department;
    }

    public Department findById(int id) {
        Session session = sessionFactory.openSession();
        Department dept = session.get(Department.class, id);
        session.close();
        return dept;
    }

    public List<Department> findAll() {
        Session session = sessionFactory.openSession();
        List<Department> list = session.createQuery("from Department", Department.class).list();
        session.close();
        return list;
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Department dept = session.get(Department.class, id);
        if (dept != null) {
            session.delete(dept);
        }
        tx.commit();
        session.close();
    }
}
