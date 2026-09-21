package com.edupulse.resultservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edupulse.resultservice.client.StudentClient;
import com.edupulse.resultservice.client.StudentResponse;
import com.edupulse.resultservice.entity.Result;
import com.edupulse.resultservice.repository.ResultRepository;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final StudentClient studentClient;

    public ResultService(
            ResultRepository resultRepository,
            StudentClient studentClient) {

        this.resultRepository = resultRepository;
        this.studentClient = studentClient;
    }

    public Result createResult(Result result) {

        StudentResponse student =
                studentClient.getStudentById(result.getStudentId());

        if (student == null) {
            return null;
        }

        return resultRepository.save(result);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElse(null);
    }

    public Result updateResult(Long id, Result resultDetails) {

        Result result = resultRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        StudentResponse student =
                studentClient.getStudentById(resultDetails.getStudentId());

        if (student == null) {
            return null;
        }

        result.setStudentId(resultDetails.getStudentId());
        result.setSubject(resultDetails.getSubject());
        result.setMarks(resultDetails.getMarks());
        result.setGrade(resultDetails.getGrade());

        return resultRepository.save(result);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}