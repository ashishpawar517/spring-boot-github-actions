package com.example.springapptest.database;

import com.example.springapptest.model.Student;
import java.util.Optional;

public interface IStudentRepository {

    void insertStudent(Student student);

    Optional<Student> getStudent(Long id);

    Optional<Integer> getCount();
}
