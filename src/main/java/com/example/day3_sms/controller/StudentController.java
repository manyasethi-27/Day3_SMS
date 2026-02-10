package com.example.day3_sms.controller;


import com.example.day3_sms.dto.StudentRequestDto;
import com.example.day3_sms.dto.StudentResponseDto;
import com.example.day3_sms.model.StudentModel;
import com.example.day3_sms.service.StudentService;
import com.example.day3_sms.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private void checkToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            throw new RuntimeException("Invalid Token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    //create function api
    @PostMapping("/students")
    public StudentResponseDto addStudent(
            @RequestHeader("Authoriztion") String authHeader,
            @Valid @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.addStudent(student);
    }

    //display students
    @GetMapping("/students")
    public List<StudentResponseDto> getStudents(
            @RequestHeader(value = "Authorization", required = false) String authHeader){
        checkToken(authHeader);
        return service.getAllStudents();
    }

    //updating
    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@PathVariable String id, @RequestBody StudentRequestDto student){
        return service.updateStudent(id, student);
    }

//    @PatchMapping("/patch/{id}")
//    public StudentResponseDto patchStudent(
//            @PathVariable String id,
//            @RequestBody StudentRequestDto student) {
//
//        return service.patchStudent(id, student);
//    }
//
//    //deletion
//    @DeleteMapping("/deleteid/{id}")
//    public StudentResponseDto deleteById(@PathVariable String id){
//        return service.deleteById(id);
//    }
}