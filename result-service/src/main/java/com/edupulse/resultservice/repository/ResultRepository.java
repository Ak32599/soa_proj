package com.edupulse.resultservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edupulse.resultservice.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

}