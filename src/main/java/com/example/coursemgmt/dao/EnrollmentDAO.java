package com.example.coursemgmt.dao;

import com.example.coursemgmt.model.Enrollment;
import java.util.List;

public interface EnrollmentDAO {
    boolean addEnrollment(Enrollment enrollment);
    Enrollment getEnrollmentById(int id);
    List<Enrollment> getAllEnrollments();
    boolean deleteEnrollment(int id);
}
