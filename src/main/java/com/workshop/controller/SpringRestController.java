package com.workshop.controller;

import com.workshop.bean.Student;
import com.workshop.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msjo on 6/14/2015.
 */
@RestController
@RequestMapping("/students")
public class SpringRestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentService.findAll();
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Student findById(@PathVariable("id") String anId) {
        return studentService.findStudentById(Integer.parseInt(anId));
    }


    @RequestMapping(value = "search/{query}", method = RequestMethod.GET)
    public List<Student> search(@PathVariable("query") String aQuery) {
        // If student is not static return new list.
        List<Student> newList = new ArrayList<Student>();
        newList.add(studentService.findStudentByName(aQuery));
        return newList;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") int anId) {
        studentService.removeStudentById(anId);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Student update(@RequestBody Student aStudent) {
        return studentService.updateStudent(aStudent);
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    // @RequestBody annotated parameter is expected to hold the entire body of request.
    // Can't use two separate parameters(String aName, int anAge) {
    public Student addStudent(@RequestBody Student aStudent) {

        Student newStudent = new Student();
        newStudent.setName(aStudent.getName());
        newStudent.setAge(aStudent.getAge());
        newStudent.setId(studentService.generateNextId());
        studentService.findAll().add(newStudent);
        return newStudent;
    }


}
