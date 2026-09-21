package com.edupulse.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edupulse.studentservice.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}