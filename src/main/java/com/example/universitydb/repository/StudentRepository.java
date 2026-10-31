package com.example.universitydb.repository;

import com.example.universitydb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Derived query: finds student by their unique student code
    Optional<Student> findByStudentCode(String studentCode);

    // Derived query: performs case-insensitive substring search on student's full name
    List<Student> findByFullNameContainingIgnoreCase(String text);

    // Derived query: retrieves all students ordered alphabetically by full name ascending
    List<Student> findAllByOrderByFullNameAsc();

    // Derived query: checks if a student with the given email exists
    boolean existsByEmail(String email);

    // Derived query: counts students whose email ends with the specified domain suffix
    long countByEmailEndingWith(String domain);
}
