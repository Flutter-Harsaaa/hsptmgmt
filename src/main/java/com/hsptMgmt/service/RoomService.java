package com.hsptMgmt.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.hsptMgmt.dao.PatientDAO;
import com.hsptMgmt.dao.RoomDAO;
import com.hsptMgmt.entity.Patient;
import com.hsptMgmt.entity.Room;

//RoomService.java
public class RoomService {
 private RoomDAO roomDAO;
 private PatientDAO patientDAO;

 public RoomService(SessionFactory sessionFactory) {
     this.roomDAO = new RoomDAO(sessionFactory);
     this.patientDAO = new PatientDAO(sessionFactory);
 }

 public Room allocateRoom(Long patientId, Long roomNumber) {
	    Patient patient = patientDAO.findById(patientId);
	    if (patient == null) {
	        throw new IllegalArgumentException("Patient not found with ID: " + patientId);
	    }

	    Room room = new Room();
	    room.setRoomId(roomNumber);
	    room.setOccupied(true);
	    room.setPatient(patient);

	    return roomDAO.save(room);
	}


 public void dischargePatient(Long patientId) {
	    Room room = roomDAO.findByPatientId(patientId);
	    if (room != null) {
	        room.setOccupied(false);
	        room.setPatient(null);
	        roomDAO.save(room); // update
	    }
	}


 public List<Room> getAllRooms() {
     return roomDAO.findAll();
 }
}

