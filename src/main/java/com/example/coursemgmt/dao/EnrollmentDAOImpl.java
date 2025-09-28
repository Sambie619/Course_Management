package com.example.coursemgmt.dao;

import com.example.coursemgmt.model.Enrollment;
import com.example.coursemgmt.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    private Connection conn;

    public EnrollmentDAOImpl() {
        conn = DBConnection.getConnection();
    }


    @Override
    public boolean addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (student_id, course_id) VALUES (?, ?)";
        try {
            conn.setAutoCommit(false); // start transaction

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, enrollment.getStudentId());
                stmt.setInt(2, enrollment.getCourseId());
                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    conn.commit(); // success ✅
                    return true;
                } else {
                    conn.rollback(); // rollback ❌
                    return false;
                }
            }

        } catch (SQLException e) {
            try {
                conn.rollback(); // rollback if error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true); // reset auto-commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Enrollment getEnrollmentById(int id) {
        String sql = "SELECT * FROM Enrollment WHERE enrollment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getTimestamp("enrollment_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getTimestamp("enrollment_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteEnrollment(int id) {
        String sql = "DELETE FROM Enrollment WHERE enrollment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
