package com.example.day3_sms.service;


import com.example.day3_sms.model.StudentModel;
import com.example.day3_sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    //Create
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }

    //Read
    public List<StudentModel> getStudents(){
        return repository.findAll();
    }

    //update
    public StudentModel updateStudent(String id, StudentModel student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
    return repository.save(existingStudent);
    }
    public StudentModel deleteById(String id){
        StudentModel existingId = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Id found"));
        repository.deleteById(id);
        return existingId;
    }

}
