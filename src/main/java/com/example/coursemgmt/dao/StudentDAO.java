package com.example.coursemgmt.dao;

import com.example.coursemgmt.model.Student;

import java.util.List;

public interface StudentDAO {
    boolean addStudent(Student student);      // CREATE
    Student getStudentById(int id);           // READ single
    List<Student> getAllStudents();           // READ all
    boolean updateStudent(Student student);   // UPDATE
    boolean deleteStudent(int id);            // DELETE

    List<Student> searchStudentsByName(String keyword);
    List<Student> searchStudentsByEmail(String keyword);

}

