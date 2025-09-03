package com.hsptMgmt.service;

import com.hsptMgmt.dao.HospitalStaffDAO;
import com.hsptMgmt.entity.HospitalStaff;

import java.util.List;

public class HospitalStaffService {
    private final HospitalStaffDAO hospitalStaffDAO;

    public HospitalStaffService(HospitalStaffDAO hospitalStaffDAO) {
        this.hospitalStaffDAO = hospitalStaffDAO;
    }

    public HospitalStaff registerStaff(HospitalStaff staff) {
        return hospitalStaffDAO.save(staff);
    }

    public HospitalStaff addStaff(HospitalStaff staff) { return hospitalStaffDAO.save(staff); }

    public HospitalStaff getStaff(Long id) { return hospitalStaffDAO.findById(id); }

    public List<HospitalStaff> getAllStaff() { return hospitalStaffDAO.findAll(); }

    public void deleteStaff(Long id) { hospitalStaffDAO.delete(id); }
}
