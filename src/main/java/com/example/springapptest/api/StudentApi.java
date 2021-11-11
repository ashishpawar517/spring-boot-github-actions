package com.example.springapptest.api;

import com.example.springapptest.model.Student;
import com.example.springapptest.service.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find Count of Students", description = "Returns total number of Students", tags = {"Student"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class))))})
    @GetMapping("/getStudent")
    @RequestMapping(value = "/getCount", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getCount() {
        return new ResponseEntity<>(studentService.getStudentCount(), HttpStatus.OK);
    }

    @Operation(summary = "Find Students by ID", description = "Name search by ID format", tags = {"Student"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class))))})
    @GetMapping("/getStudent")
    public ResponseEntity<?> getStudent(@RequestParam("id") Long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @Operation(summary = "Dummy API", description = "return Hello World")
    @GetMapping("/greet")
    public ResponseEntity<?> greet() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @Operation(summary = "Add a new Student", description = "", tags = { "Student" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student created",
            content = @Content(schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Student already exists") })
    @PostMapping("/insertStudent")
    public ResponseEntity<?> insertStudent(HttpServletRequest request, @RequestBody Student studentRequest,
        BindingResult result) {
        studentService.insertStudent(studentRequest);
        return new ResponseEntity<>("Student details added.", HttpStatus.OK);
    }

}
