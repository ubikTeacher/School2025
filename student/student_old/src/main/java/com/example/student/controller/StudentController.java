package com.example.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.service.StudentService;
import com.example.student.model.Student;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/studentList")
    public String studentList(Model model) {
        //サービスにデータを取得してもらう
        List<Student> students=service.getStudents();
        //取得した学生情報をモデルにセット
        model.addAttribute("students", students);
        //studentListページへ。
        return "studentList"; // students.html に渡す
    }
}
