package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/test")
    public String showForm() {
        return "test"; // register.html を表示
    }

    @PostMapping("/result")
    public String submitStudent(@RequestParam("txtNo") int txtNo,
                                @RequestParam("txtName") String txtName,
                                @RequestParam("seibetu") int seibetu,
                                Model model) {
        Student student = new Student();
        student.setStudentId(txtNo);
        student.setStudentName(txtName);
        student.setStudentSeibetu(seibetu);

        service.registerStudent(student);


        return "result"; // result.html に一覧表示
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = service.getStudents();
        model.addAttribute("students", students);
        return "students"; // students.html に渡す
    }
}