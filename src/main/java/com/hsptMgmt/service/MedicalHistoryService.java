package com.hsptMgmt.service;

import com.hsptMgmt.dao.MedicalHistoryDAO;
import com.hsptMgmt.entity.MedicalHistory;

import java.util.List;

import org.hibernate.SessionFactory;

public class MedicalHistoryService {
    private final MedicalHistoryDAO medicalHistoryDAO;

    public MedicalHistoryService(SessionFactory sessionFactory) {
        this.medicalHistoryDAO = new MedicalHistoryDAO(sessionFactory);
    }

    public MedicalHistory addHistory(MedicalHistory h) { return medicalHistoryDAO.save(h); }

    public MedicalHistory getHistory(Long id) { return medicalHistoryDAO.findById(id); }

    public List<MedicalHistory> getAllHistory() { return medicalHistoryDAO.findAll(); }

    public List<MedicalHistory> getHistoryByPatient(Long patientId) {
        return medicalHistoryDAO.findByPatientId(patientId);
    }

    public void deleteHistory(Long id) { medicalHistoryDAO.delete(id); }
}
