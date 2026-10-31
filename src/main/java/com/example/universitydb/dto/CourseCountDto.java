package com.example.universitydb.dto;

public record CourseCountDto(String courseCode, String title, Long studentCount) {

    public static CourseCountDto from(CourseEnrollmentCount count) {
        if (count == null) {
            return null;
        }
        return new CourseCountDto(
                count.getCourseCode(),
                count.getTitle(),
                count.getStudentCount()
        );
    }
}
