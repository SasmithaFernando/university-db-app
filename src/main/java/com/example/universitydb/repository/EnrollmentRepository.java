package com.example.universitydb.repository;

import com.example.universitydb.dto.CourseEnrollmentCount;
import com.example.universitydb.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Derived query: traverses student relationship to find enrollments by student code
    List<Enrollment> findByStudent_StudentCode(String studentCode);

    // Derived query: traverses course relationship to find enrollments by course code
    List<Enrollment> findByCourse_Code(String courseCode);

    // Derived query: finds enrollments by exact grade
    List<Enrollment> findByGrade(String grade);

    // Derived query: finds enrollments where grade matches any value in the collection
    List<Enrollment> findByGradeIn(Collection<String> grades);

    // Derived query: finds enrollments matching student code and grade
    List<Enrollment> findByStudent_StudentCodeAndGrade(String studentCode, String grade);

    // Derived query: counts enrollments for a given course code
    long countByCourse_Code(String courseCode);

    // Custom JPQL query: aggregates enrollment counts grouped by course code and title, ordered descending by count
    @Query("SELECT c.code AS courseCode, c.title AS title, COUNT(e) AS studentCount FROM Enrollment e JOIN e.course c GROUP BY c.code, c.title ORDER BY COUNT(e) DESC, c.code")
    List<CourseEnrollmentCount> countStudentsPerCourse();
}
