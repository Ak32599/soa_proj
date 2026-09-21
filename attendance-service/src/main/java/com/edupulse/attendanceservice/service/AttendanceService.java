package com.edupulse.attendanceservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import feign.FeignException;

import com.edupulse.attendanceservice.client.StudentClient;
import com.edupulse.attendanceservice.client.StudentResponse;
import com.edupulse.attendanceservice.entity.Attendance;
import com.edupulse.attendanceservice.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentClient studentClient;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            StudentClient studentClient) {

        this.attendanceRepository = attendanceRepository;
        this.studentClient = studentClient;
    }

    public Attendance createAttendance(Attendance attendance) {

        try {

            StudentResponse student =
                    studentClient.getStudentById(attendance.getStudentId());

            if (student == null) {
                return null;
            }

            return attendanceRepository.save(attendance);

        } catch (FeignException.NotFound e) {

            return null;
        }
    }

    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }

    public Attendance getAttendanceById(Long id) {

        return attendanceRepository.findById(id).orElse(null);
    }

    public List<Attendance> getAttendanceByStudentId(Long studentId) {

        return attendanceRepository.findByStudentId(studentId);
    }

    public Attendance updateAttendance(
            Long id,
            Attendance attendanceDetails) {

        Attendance attendance =
                attendanceRepository.findById(id).orElse(null);

        if (attendance == null) {
            return null;
        }

        try {

            StudentResponse student =
                    studentClient.getStudentById(
                            attendanceDetails.getStudentId());

            if (student == null) {
                return null;
            }

        } catch (FeignException.NotFound e) {

            return null;
        }

        attendance.setStudentId(attendanceDetails.getStudentId());
        attendance.setDate(attendanceDetails.getDate());
        attendance.setStatus(attendanceDetails.getStatus());

        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {

        attendanceRepository.deleteById(id);
    }
}