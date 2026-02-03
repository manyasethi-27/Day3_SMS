package com.example.day3_sms.service;


import com.example.day3_sms.dto.StudentRequestDto;
import com.example.day3_sms.dto.StudentResponseDto;
import com.example.day3_sms.exception.StudentNotFoundException;
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
//    public StudentModel addStudent(StudentModel student){
//        return repository.save(student);
//    }

    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }

    //Read
//    public List<StudentModel> getStudents(){
//        return repository.findAll();
//    }
    public List<StudentResponseDto> getAllStudents(){
        return repository.findAll()
                .stream()//buffer
                .map(s-> new StudentResponseDto(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                )).toList();
    }

    //update
//    public StudentModel updateStudent(String id, StudentModel student){
//        StudentModel existingStudent = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("No Student found"));
//        existingStudent.setName(student.getName());
//        existingStudent.setAge(student.getAge());
//        existingStudent.setEmail(student.getEmail());
//    return repository.save(existingStudent);
//    }

    public StudentResponseDto updateStudent(String id, StudentModel student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No Student found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        StudentModel updatedStudent = repository.save(existingStudent);
        return new StudentResponseDto(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getAge(),
                updatedStudent.getEmail()
        );
    }

//    public StudentModel deleteById(String id){
//        StudentModel existingId = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("No Id found"));
//        repository.deleteById(id);
//        return existingId;
//    }
public StudentResponseDto deleteById(String id) {
    StudentModel existingStudent = repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException("No id"));

    repository.deleteById(id);

    return new StudentResponseDto(
            existingStudent.getId(),
            existingStudent.getName(),
            existingStudent.getAge(),
            existingStudent.getEmail()
    );
}


}
