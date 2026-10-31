package com.example.universitydb.dto;

import com.example.universitydb.model.Course;

public record CourseDto(String code, String title, Integer credits, String lecturer, String department) {

    public static CourseDto from(Course course) {
        if (course == null) {
            return null;
        }
        String lecturerName = course.getLecturer() != null
                ? course.getLecturer().getName()
                : null;
        String departmentName = (course.getLecturer() != null && course.getLecturer().getDepartment() != null)
                ? course.getLecturer().getDepartment().getName()
                : null;
        return new CourseDto(
                course.getCode(),
                course.getTitle(),
                course.getCredits(),
                lecturerName,
                departmentName
        );
    }
}
