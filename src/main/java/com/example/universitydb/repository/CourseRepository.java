package com.example.universitydb.repository;

import com.example.universitydb.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Derived query: finds course by unique course code
    Optional<Course> findByCode(String code);

    // Derived query: finds courses with credits greater than or equal to the specified value
    List<Course> findByCreditsGreaterThanEqual(Integer credits);

    // Derived query: performs case-insensitive substring search on course title
    List<Course> findByTitleContainingIgnoreCase(String text);

    // Derived query: traverses relationship to find courses taught by a lecturer with matching name
    List<Course> findByLecturer_Name(String lecturerName);

    // Derived query: traverses relationships to find courses where lecturer belongs to department with matching name
    List<Course> findByLecturer_Department_Name(String departmentName);
}
