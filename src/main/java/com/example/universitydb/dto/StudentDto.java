package com.example.universitydb.dto;

import com.example.universitydb.model.Student;

public record StudentDto(String studentCode, String fullName, String email) {

    public static StudentDto from(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentDto(
                student.getStudentCode(),
                student.getFullName(),
                student.getEmail()
        );
    }
}
