package com.example.springapptest.api;

import com.example.springapptest.model.Student;
import com.example.springapptest.service.IStudentService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentApi {

    @Autowired
    IStudentService studentService;

    @RequestMapping(value = "/getCount", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getCount(){
        return new ResponseEntity<>(studentService.getStudentCount(), HttpStatus.OK);
    }

    @GetMapping("/getStudent")
    public ResponseEntity<?> getStudent(@RequestParam("id") Long id){
        return new ResponseEntity<>(studentService.getStudent(id),HttpStatus.OK);
    }

    @GetMapping("/greet")
    public ResponseEntity<?> greet(){
        return new ResponseEntity<>("hello world",HttpStatus.OK);
    }
    @PostMapping("/insertStudent")
    public ResponseEntity<?> insertStudent(HttpServletRequest request,@RequestBody Student studentRequest,
        BindingResult result){
        studentService.insertStudent(studentRequest);
        return new ResponseEntity<>("Student details added.",HttpStatus.OK);
    }

}
