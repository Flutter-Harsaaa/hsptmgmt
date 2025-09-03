// com/hsptMgmt/service/DepartmentService.java
package com.hsptMgmt.service;

import com.hsptMgmt.dao.DepartmentDAO;
import com.hsptMgmt.entity.Department;
import java.util.List;

public class DepartmentService {
    private DepartmentDAO departmentDAO;

    public DepartmentService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public Department addDepartment(Department department) {
        return departmentDAO.save(department);
    }

    public Department getDepartment(int id) {
        return departmentDAO.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentDAO.findAll();
    }

    public void deleteDepartment(int id) {
        departmentDAO.delete(id);
    }
}
