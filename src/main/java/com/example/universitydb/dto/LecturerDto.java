package com.example.universitydb.dto;

import com.example.universitydb.model.Lecturer;

public record LecturerDto(String name, String email, String department) {

    public static LecturerDto from(Lecturer lecturer) {
        if (lecturer == null) {
            return null;
        }
        String departmentName = lecturer.getDepartment() != null
                ? lecturer.getDepartment().getName()
                : null;
        return new LecturerDto(
                lecturer.getName(),
                lecturer.getEmail(),
                departmentName
        );
    }
}
