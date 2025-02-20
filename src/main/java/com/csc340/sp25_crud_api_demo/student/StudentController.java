package com.csc340.sp25_crud_api_demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * StudentController.java.
 * Includes all REST API endpoint mappings for the Student object.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    /**
     * Get a list of all Students in the database.
     * http://localhost:8080/students/all
     *
     * @return a list of Students  objects.
     */
    @GetMapping("/all")
    public Object getAllStudents() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);

    }

    /**
     * Get a specific Student by Id.
     * http://localhost:8080/students/2
     *
     * @param studentId the unique Id for a Student.
     * @return One Student object.
     */
    @GetMapping("/{studentId}")
    public Object getOneStudent(@PathVariable int studentId) {
        return new ResponseEntity<>(service.getStudentById(studentId), HttpStatus.OK);

    }


    /**
     * Get a list of students with a name that contains the given string.
     * http://localhost:8080/students/name?search=alex
     *
     * @param search the search key
     * @return list of Student objects matching the search key.
     */
    @GetMapping("/name")
    public Object getStudentsByName(@RequestParam(name = "search", defaultValue = "") String search) {
        return new ResponseEntity<>(service.getStudentsByName(search), HttpStatus.OK);

    }

    /**
     * Get a list of Students based on their major.
     * http://localhost:8080/students/major/csc
     *
     * @param major the search key.
     * @return A list of Student objects matching the search key.
     */
    @GetMapping("/major/{major}")
    public Object getStudentsByMajor(@PathVariable String major) {
        return new ResponseEntity<>(service.getStudentsByMajor(major), HttpStatus.OK);
    }


    /**
     * Get a list of students with a GPA above a threshold.
     * http://localhost:8080/students/honors?gpa=3.6
     *
     * @param gpa the minimum GPA
     * @return list of Student objects matching the search key.
     */
    @GetMapping("/honors")
    public Object getHonorsStudents(@RequestParam(name = "gpa", defaultValue = "3.0") double gpa) {
        return new ResponseEntity<>(service.getHonorsStudents(gpa), HttpStatus.OK);

    }

    /**
     * Create a new Student entry.
     * http://localhost:8080/students/new --data '{  "name": "sample new student", "major": "csc", "gpa": 3.55}'
     *
     * @param student the new Student object.
     * @return the updated list of Students.
     */
    @PostMapping("/new")
    public Object addNewStudent(@RequestBody Student student) {
        service.addNewStudent(student);
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.CREATED);

    }

    /**
     * Update an existing Student object.
     * http://localhost:8080/students/update/2 --data '{ "studentId": 1, "name": "sampleUpdated", "major": "csc", "gpa": 3.92}'
     *
     * @param studentId the unique Student Id.
     * @param student   the new update Student details.
     * @return the updated Student object.
     */
    @PutMapping("/update/{studentId}")
    public Object updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        service.updateStudent(studentId, student);
        return new ResponseEntity<>(service.getStudentById(studentId), HttpStatus.CREATED);

    }

    /**
     * Delete a Student object.
     * http://localhost:8080/students/delete/2
     *
     * @param studentId the unique Student Id.
     * @return the updated list of Students.
     */
    @DeleteMapping("/delete/{studentId}")
    public Object deleteStudentById(@PathVariable int studentId) {
        service.deleteStudentById(studentId);
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }
}
