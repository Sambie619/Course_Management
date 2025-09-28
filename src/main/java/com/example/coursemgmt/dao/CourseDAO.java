package com.example.coursemgmt.dao;

import com.example.coursemgmt.model.Course;
import java.util.List;

public interface CourseDAO {
    boolean addCourse(Course course);           // CREATE
    Course getCourseById(int id);               // READ single
    List<Course> getAllCourses();               // READ all
    boolean updateCourse(Course course);        // UPDATE
    boolean deleteCourse(int id);               // DELETE

    List<Course> searchCoursesByName(String keyword);

}
