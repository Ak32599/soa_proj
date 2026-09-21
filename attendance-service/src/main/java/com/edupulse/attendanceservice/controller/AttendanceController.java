package com.edupulse.attendanceservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.edupulse.attendanceservice.entity.Attendance;
import com.edupulse.attendanceservice.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // FACULTY and ADMIN can create attendance
    @PostMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<Attendance> createAttendance(
            @RequestBody Attendance attendance) {

        Attendance createdAttendance =
                attendanceService.createAttendance(attendance);

        if (createdAttendance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(createdAttendance);
    }

    // FACULTY and ADMIN can view all attendance
    @GetMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    // FACULTY and ADMIN can view one attendance record
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<Attendance> getAttendanceById(
            @PathVariable Long id) {

        Attendance attendance =
                attendanceService.getAttendanceById(id);

        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(attendance);
    }

    // FACULTY and ADMIN can view attendance of a student
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<List<Attendance>> getAttendanceByStudentId(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByStudentId(studentId)
        );
    }

    // FACULTY and ADMIN can update attendance
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long id,
            @RequestBody Attendance attendanceDetails) {

        Attendance updatedAttendance =
                attendanceService.updateAttendance(id, attendanceDetails);

        if (updatedAttendance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedAttendance);
    }

    // Only ADMIN can delete attendance
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long id) {

        Attendance attendance =
                attendanceService.getAttendanceById(id);

        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }

        attendanceService.deleteAttendance(id);

        return ResponseEntity.noContent().build();
    }
}

//package com.edupulse.attendanceservice.controller;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.edupulse.attendanceservice.entity.Attendance;
//import com.edupulse.attendanceservice.service.AttendanceService;
//
//@RestController
//@RequestMapping("/attendance")
//public class AttendanceController {
//
//    private final AttendanceService attendanceService;
//
//    public AttendanceController(AttendanceService attendanceService) {
//        this.attendanceService = attendanceService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Attendance> createAttendance(
//            @RequestBody Attendance attendance) {
//
//        Attendance createdAttendance =
//                attendanceService.createAttendance(attendance);
//
//        if (createdAttendance == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(createdAttendance);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Attendance>> getAllAttendance() {
//
//        return ResponseEntity.ok(
//                attendanceService.getAllAttendance()
//        );
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Attendance> getAttendanceById(
//            @PathVariable Long id) {
//
//        Attendance attendance =
//                attendanceService.getAttendanceById(id);
//
//        if (attendance == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(attendance);
//    }
//
//    @GetMapping("/student/{studentId}")
//    public ResponseEntity<List<Attendance>> getAttendanceByStudentId(
//            @PathVariable Long studentId) {
//
//        return ResponseEntity.ok(
//                attendanceService.getAttendanceByStudentId(studentId)
//        );
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Attendance> updateAttendance(
//            @PathVariable Long id,
//            @RequestBody Attendance attendanceDetails) {
//
//        Attendance updatedAttendance =
//                attendanceService.updateAttendance(
//                        id,
//                        attendanceDetails
//                );
//
//        if (updatedAttendance == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(updatedAttendance);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAttendance(
//            @PathVariable Long id) {
//
//        Attendance attendance =
//                attendanceService.getAttendanceById(id);
//
//        if (attendance == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        attendanceService.deleteAttendance(id);
//
//        return ResponseEntity.noContent().build();
//    }
//}