package com.example.day3_sms.controller;


import com.example.day3_sms.model.StudentModel;
import com.example.day3_sms.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //display students
    @GetMapping("/students")
    public List<StudentModel> getStudents(){
        return service.getStudents();
    }

    //updating
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student){
        return service.updateStudent(id, student);
    }

    //deletion
    @DeleteMapping("/deleteid/{id}")
    public StudentModel deleteById(@PathVariable String id){
        return service.deleteById(id);
    }
}