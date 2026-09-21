package com.edupulse.resultservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.edupulse.resultservice.entity.Result;
import com.edupulse.resultservice.service.ResultService;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    // FACULTY and ADMIN can create results
    @PostMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        Result createdResult = resultService.createResult(result);

        if (createdResult == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(createdResult);
    }

    // FACULTY and ADMIN can view all results
    @GetMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<List<Result>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    // FACULTY and ADMIN can view one result
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<Result> getResultById(@PathVariable Long id) {

        Result result = resultService.getResultById(id);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    // FACULTY and ADMIN can update results
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<Result> updateResult(
            @PathVariable Long id,
            @RequestBody Result resultDetails) {

        Result updatedResult =
                resultService.updateResult(id, resultDetails);

        if (updatedResult == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedResult);
    }

    // Only ADMIN can delete a result
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {

        Result result = resultService.getResultById(id);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        resultService.deleteResult(id);

        return ResponseEntity.noContent().build();
    }
}