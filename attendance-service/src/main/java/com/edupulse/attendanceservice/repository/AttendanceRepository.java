package com.edupulse.attendanceservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edupulse.attendanceservice.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

}