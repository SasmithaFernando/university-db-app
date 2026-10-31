package com.example.universitydb.dto;

import com.example.universitydb.model.Enrollment;

public record EnrollmentDto(
        String studentCode,
        String studentName,
        String courseCode,
        String courseTitle,
        String grade) {

    public static EnrollmentDto from(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }
        String studentCode = enrollment.getStudent() != null
                ? enrollment.getStudent().getStudentCode()
                : null;
        String studentName = enrollment.getStudent() != null
                ? enrollment.getStudent().getFullName()
                : null;
        String courseCode = enrollment.getCourse() != null
                ? enrollment.getCourse().getCode()
                : null;
        String courseTitle = enrollment.getCourse() != null
                ? enrollment.getCourse().getTitle()
                : null;
        return new EnrollmentDto(
                studentCode,
                studentName,
                courseCode,
                courseTitle,
                enrollment.getGrade()
        );
    }
}
