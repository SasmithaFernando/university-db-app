package com.example.universitydb.dto;

/**
 * Interface-based projection for student count per course.
 */
public interface CourseEnrollmentCount {
    String getCourseCode();
    String getTitle();
    Long getStudentCount();
}
