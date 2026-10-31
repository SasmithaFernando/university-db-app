package com.example.universitydb.controller;

import com.example.universitydb.dto.CourseCountDto;
import com.example.universitydb.dto.CourseDto;
import com.example.universitydb.dto.EnrollmentDto;
import com.example.universitydb.dto.LecturerDto;
import com.example.universitydb.dto.StudentDto;
import com.example.universitydb.model.Course;
import com.example.universitydb.model.Department;
import com.example.universitydb.model.Enrollment;
import com.example.universitydb.model.Student;
import com.example.universitydb.repository.CourseRepository;
import com.example.universitydb.repository.DepartmentRepository;
import com.example.universitydb.repository.EnrollmentRepository;
import com.example.universitydb.repository.LecturerRepository;
import com.example.universitydb.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UniversityController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final DepartmentRepository departmentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @GetMapping("/students")
    public List<StudentDto> getStudents(@RequestParam(required = false) String name) {
        List<Student> students;
        if (name == null || name.isBlank()) {
            students = studentRepository.findAllByOrderByFullNameAsc();
        } else {
            students = studentRepository.findByFullNameContainingIgnoreCase(name.trim());
        }
        return students.stream()
                .map(StudentDto::from)
                .toList();
    }

    @GetMapping("/students/{code}")
    public ResponseEntity<StudentDto> getStudentByCode(@PathVariable String code) {
        return studentRepository.findByStudentCode(code)
                .map(StudentDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/students/{code}/enrollments")
    public List<EnrollmentDto> getStudentEnrollments(@PathVariable String code) {
        return enrollmentRepository.findByStudent_StudentCode(code)
                .stream()
                .map(EnrollmentDto::from)
                .toList();
    }

    @GetMapping("/courses")
    public List<CourseDto> getCourses(
            @RequestParam(required = false) Integer minCredits,
            @RequestParam(required = false) String department) {
        List<Course> courses;
        if (minCredits != null) {
            courses = courseRepository.findByCreditsGreaterThanEqual(minCredits);
        } else if (department != null && !department.isBlank()) {
            courses = courseRepository.findByLecturer_Department_Name(department.trim());
        } else {
            courses = courseRepository.findAll();
        }
        return courses.stream()
                .map(CourseDto::from)
                .toList();
    }

    @GetMapping("/courses/{code}/students")
    public List<StudentDto> getStudentsByCourse(@PathVariable String code) {
        return enrollmentRepository.findByCourse_Code(code)
                .stream()
                .map(Enrollment::getStudent)
                .map(StudentDto::from)
                .toList();
    }

    @GetMapping("/departments/{code}/lecturers")
    public ResponseEntity<List<LecturerDto>> getDepartmentLecturers(@PathVariable String code) {
        return departmentRepository.findWithLecturers(code)
                .map(Department::getLecturers)
                .map(lecturers -> lecturers.stream().map(LecturerDto::from).toList())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enrollments")
    public List<EnrollmentDto> getEnrollmentsByGrade(@RequestParam String grade) {
        return enrollmentRepository.findByGrade(grade)
                .stream()
                .map(EnrollmentDto::from)
                .toList();
    }

    @GetMapping("/reports/course-counts")
    public List<CourseCountDto> getCourseCounts() {
        return enrollmentRepository.countStudentsPerCourse()
                .stream()
                .map(CourseCountDto::from)
                .toList();
    }
}
