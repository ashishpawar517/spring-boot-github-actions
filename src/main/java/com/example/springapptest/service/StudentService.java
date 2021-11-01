package com.example.springapptest.service;

import com.example.springapptest.database.IStudentRepository;
import com.example.springapptest.model.Student;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class StudentService implements IStudentService {

    @Autowired
    IStudentRepository repository;

    public Integer getStudentCount() {
        return repository.getCount().get();
    }

    public void insertStudent(Student student) {
        repository.insertStudent(student);
    }

    public Student getStudent(Long id) {

        Optional<Student> studentOptional = repository.getStudent(id);
        return studentOptional.orElseGet(Student::new);
    }

    @Override
    public String greet() {
        return "hello world";
    }


}
