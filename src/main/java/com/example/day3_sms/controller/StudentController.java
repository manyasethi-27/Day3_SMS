package com.example.day3_sms.controller;


import com.example.day3_sms.model.StudentModel;
import com.example.day3_sms.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    //create function api

    @PostMapping("add-student/")
    public StudentModel addStudent(@RequestBody StudentModel student){
        return service.addStudent(student);
    }
}