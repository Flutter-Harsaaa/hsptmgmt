package com.hsptMgmt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hsptMgmt.entity.Room;

//RoomDAO.java
public class RoomDAO {
 private SessionFactory sessionFactory;

 public RoomDAO(SessionFactory sessionFactory) {
     this.sessionFactory = sessionFactory;
 }

 public Room save(Room room) {
     Session session = sessionFactory.openSession();
     Transaction tx = session.beginTransaction();
     session.saveOrUpdate(room);
     tx.commit();
     session.close();
     return room;
 }
 
 public Room findByPatientId(Long patientId) {
	    Session session = sessionFactory.openSession();
	    Room room = session.createQuery("from Room r where r.patient.id = :pid", Room.class)
	                       .setParameter("pid", patientId)
	                       .uniqueResult();
	    session.close();
	    return room;
	}


 public void delete(Long id) {
     Session session = sessionFactory.openSession();
     Transaction tx = session.beginTransaction();
     Room room = session.get(Room.class, id);
     if (room != null) {
         session.delete(room);
     }
     tx.commit();
     session.close();
 }

 public List<Room> findAll() {
     Session session = sessionFactory.openSession();
     List<Room> rooms = session.createQuery("from Room", Room.class).list();
     session.close();
     return rooms;
 }
}

