package com.example.day3_sms.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data //no need to create getter setter, lombok will do
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")

public class StudentModel{
    @Id //primary key
    private String id;

    private String name;
    private int age;
    private String email;
}