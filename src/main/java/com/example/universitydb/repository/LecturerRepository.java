package com.example.universitydb.repository;

import com.example.universitydb.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    // Derived query: finds lecturer by unique email address
    Optional<Lecturer> findByEmail(String email);

    // Derived query: traverses department relationship to find lecturers by department code
    List<Lecturer> findByDepartment_Code(String departmentCode);
}
