package com.example.springapptest.service;

import com.example.springapptest.model.Student;
import java.util.Optional;

public interface IStudentService {

    Integer getStudentCount();

    void insertStudent(Student student);

    Student getStudent(Long id);

    String greet();
}
