
package com.hsptMgmt.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hospital_staff")
public class HospitalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String role; // Receptionist, Admin, etc.
    private String contactNumber;
    private String email;

    @OneToMany(mappedBy = "approvalBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> approvedAppointments;

    public HospitalStaff() {}

    // Getters and Setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Appointment> getApprovedAppointments() { return approvedAppointments; }
    public void setApprovedAppointments(List<Appointment> approvedAppointments) { this.approvedAppointments = approvedAppointments; }
}
