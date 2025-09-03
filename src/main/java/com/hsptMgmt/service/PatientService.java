// com/hsptMgmt/service/PatientService.java
package com.hsptMgmt.service;

import com.hsptMgmt.dao.PatientDAO;
import com.hsptMgmt.entity.Patient;
import java.util.List;

import org.hibernate.SessionFactory;

public class PatientService {
    private PatientDAO patientDAO;

    public PatientService(SessionFactory sessionFactory) {
        this.patientDAO =  new PatientDAO(sessionFactory);
    }
 


    public Patient registerPatient(Patient patient) {
        return patientDAO.save(patient);
    }

    public Patient getPatient(long id) {
        return patientDAO.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.findAll();
    }

    public void deletePatient(long id) {
        patientDAO.delete(id);
    }
    public List<Patient> searchByName(String name) {
        return patientDAO.findByName(name); // you’ll add DAO query
    }

    public long countPatients() {
        return patientDAO.count(); // typical count() method
    }

}
