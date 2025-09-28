package com.example.coursemgmt;

import com.example.coursemgmt.dao.*;
import com.example.coursemgmt.model.*;
import com.example.coursemgmt.util.PasswordUtil;
import com.example.coursemgmt.util.ValidationUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAOImpl();
    private static final CourseDAO courseDAO = new CourseDAOImpl();
    private static final EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();

    public static void main(String[] args) {
        System.out.println("🎓 Course Management System 🚀");
        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> addCourse();
                case 6 -> viewCourses();
                case 7 -> updateCourse();
                case 8 -> deleteCourse();
                case 9 -> enrollStudent();
                case 10 -> viewEnrollments();
                case 11 -> searchStudentsByName();
                case 12 -> searchStudentsByEmail();
                case 13 -> searchCoursesByName();
                case 0 -> {
                    exit = true;
                    System.out.println("Exiting... Goodbye! 👋");
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Add Course");
        System.out.println("6. View Courses");
        System.out.println("7. Update Course");
        System.out.println("8. Delete Course");
        System.out.println("9. Enroll Student in Course");
        System.out.println("10. View Enrollments");
        System.out.println("11. Search Students by Name");
        System.out.println("12. Search Students by Email");
        System.out.println("13. Search Courses by Name");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    // --- Student Operations ---
    private static void addStudent() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (!ValidationUtil.isValidName(name)) {
            System.out.println("Invalid name ❌");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("Invalid email ❌");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (!ValidationUtil.isValidPassword(password)) {
            System.out.println("Password must be at least 6 characters ❌");
            return;
        }

        Student student = new Student(name, email, PasswordUtil.hashPassword(password));
        if (studentDAO.addStudent(student)) {
            System.out.println("Student added successfully ✅");
        } else {
            System.out.println("Failed to add student ❌");
        }
    }

    private static void viewStudents() {
        List<Student> students = studentDAO.getAllStudents();
        System.out.println("\n--- Students ---");
        students.forEach(System.out::println);
    }

    private static void updateStudent() {
        System.out.print("Student ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid student ID ❌");
            scanner.nextLine();
            return;
        }
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("New Name: ");
        String name = scanner.nextLine();
        if (!ValidationUtil.isValidName(name)) {
            System.out.println("Invalid name ❌");
            return;
        }

        System.out.print("New Email: ");
        String email = scanner.nextLine();
        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("Invalid email ❌");
            return;
        }

        System.out.print("New Password: ");
        String password = scanner.nextLine();
        if (!ValidationUtil.isValidPassword(password)) {
            System.out.println("Password must be at least 6 characters ❌");
            return;
        }

        Student student = new Student(studentId, name, email, PasswordUtil.hashPassword(password));
        if (studentDAO.updateStudent(student)) {
            System.out.println("Student updated successfully ✅");
        } else {
            System.out.println("Failed to update student ❌");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (studentDAO.deleteStudent(id)) {
            System.out.println("Student deleted successfully ✅");
        } else {
            System.out.println("Failed to delete student ❌");
        }
    }

    // --- Course Operations ---
    private static void addCourse() {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        if (!ValidationUtil.isValidName(name)) {
            System.out.println("Invalid course name ❌");
            return;
        }

        System.out.print("Description: ");
        String description = scanner.nextLine();
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Description cannot be empty ❌");
            return;
        }

        Course course = new Course(name, description);
        if (courseDAO.addCourse(course)) {
            System.out.println("Course added successfully ✅");
        } else {
            System.out.println("Failed to add course ❌");
        }
    }

    private static void viewCourses() {
        List<Course> courses = courseDAO.getAllCourses();
        System.out.println("\n--- Courses ---");
        courses.forEach(System.out::println);
    }

    private static void updateCourse() {
        System.out.print("Course ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid course ID ❌");
            scanner.nextLine();
            return;
        }
        int courseId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("New Course Name: ");
        String name = scanner.nextLine();
        if (!ValidationUtil.isValidName(name)) {
            System.out.println("Invalid course name ❌");
            return;
        }

        System.out.print("New Description: ");
        String description = scanner.nextLine();
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Description cannot be empty ❌");
            return;
        }

        Course course = new Course(courseId, name, description);
        if (courseDAO.updateCourse(course)) {
            System.out.println("Course updated successfully ✅");
        } else {
            System.out.println("Failed to update course ❌");
        }
    }

    private static void deleteCourse() {
        System.out.print("Enter Course ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (courseDAO.deleteCourse(id)) {
            System.out.println("Course deleted successfully ✅");
        } else {
            System.out.println("Failed to delete course ❌");
        }
    }

    // --- Enrollment Operations ---
    private static void enrollStudent() {
        System.out.print("Student ID: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid student ID ❌");
            scanner.nextLine();
            return;
        }
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Course ID: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid course ID ❌");
            scanner.nextLine();
            return;
        }
        int courseId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Enrollment enrollment = new Enrollment(studentId, courseId);
        if (enrollmentDAO.addEnrollment(enrollment)) {
            System.out.println("Enrollment successful ✅");
        } else {
            System.out.println("Failed to enroll student ❌ (maybe already enrolled?)");
        }
    }

    private static void viewEnrollments() {
        List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
        System.out.println("\n--- Enrollments ---");
        enrollments.forEach(System.out::println);
    }

    private static void searchStudentsByName() {
        System.out.print("Enter keyword for name search: ");
        String keyword = scanner.nextLine();
        List<Student> students = studentDAO.searchStudentsByName(keyword);
        students.forEach(System.out::println);
    }

    private static void searchStudentsByEmail() {
        System.out.print("Enter keyword for email search: ");
        String keyword = scanner.nextLine();
        List<Student> students = studentDAO.searchStudentsByEmail(keyword);
        students.forEach(System.out::println);
    }

    private static void searchCoursesByName() {
        System.out.print("Enter keyword for course name search: ");
        String keyword = scanner.nextLine();
        List<Course> courses = courseDAO.searchCoursesByName(keyword);
        courses.forEach(System.out::println);
    }
}

