package com.workshop.service;

import com.workshop.bean.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mijo on 2016-07-05.
 */
@Service
public class StudentService {

    private static List<Student> students = new ArrayList<Student>();

    public StudentService() {
        // Populate test data. Later each WS method will retrieve data from DB.
        students.add(new Student(1001, "Mike", 17));
        students.add(new Student(1002, "Jane", 19));
        students.add(new Student(1003, "Bob", 19));
        students.add(new Student(1004, "Susan", 22));
        students.add(new Student(1005, "Daniel", 25));
        students.add(new Student(1006, "John", 26));
        students.add(new Student(1007, "Debbie", 28));
    }


    public List<Student> findAll() {
        return students;
    }

    public Student findStudentById(int anId) {
        for (Student student : students) {
            if (student.getId() == anId) {
                return student;
            }
        }
        return null;
    }

    public Student findStudentByName(String aName) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(aName)) {
                return student;
            }
        }
        return null;
    }

    public void removeStudentById(int anId) {
        ListIterator<Student> iter = students.listIterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == anId) {
                iter.remove();
            }
        }
    }

    public Student updateStudent(Student aStudent) {

        Student newStudent = new Student();
        newStudent.setId(aStudent.getId());
        newStudent.setName(aStudent.getName());
        newStudent.setAge(aStudent.getAge());

        removeStudentById(aStudent.getId());
        students.add(newStudent);

        return newStudent;
    }

    public int generateNextId() {
        Collections.sort(students, Student.idComparator);
        return students.get(0).getId() + 1;
    }
}
