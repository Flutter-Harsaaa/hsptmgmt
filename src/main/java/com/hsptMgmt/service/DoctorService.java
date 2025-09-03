// com/hsptMgmt/service/DoctorService.java
package com.hsptMgmt.service;

import com.hsptMgmt.dao.DoctorDAO;
import com.hsptMgmt.entity.Doctor;
import java.util.List;

import org.hibernate.SessionFactory;

public class DoctorService {
    private DoctorDAO doctorDAO;

    public DoctorService(SessionFactory sessionFactory) {
        this.doctorDAO = new DoctorDAO(sessionFactory);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorDAO.save(doctor);
    }

    public Doctor getDoctor(int id) {
        return doctorDAO.findById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorDAO.findAll();
    }

    public void deleteDoctor(int id) {
        doctorDAO.delete(id);
    }
    
    public List<Doctor> searchBySpecialization(String specialization) {
        return doctorDAO.findBySpecialization(specialization);
    }

    public long countDoctors() {
        return doctorDAO.count();
    }

}
