package com.example.universitydb.repository;

import com.example.universitydb.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Derived query: finds department by unique code
    Optional<Department> findByCode(String code);

    // Custom JPQL query: eager fetches the department along with its collection of lecturers using LEFT JOIN FETCH
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.lecturers WHERE d.code = :code")
    Optional<Department> findWithLecturers(@Param("code") String code);
}
