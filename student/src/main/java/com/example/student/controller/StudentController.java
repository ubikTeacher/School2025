package com.example.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.service.StudentService;
import com.example.student.model.Student;
import java.util.List;


@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    //学生一覧ページ表示時
    @GetMapping("/studentList")
    public String test(Model model){
        //サービスにデータを取得してもらう
        List<Student> students=service.getStudents();
        //取得した学生情報をモデルにセット
        model.addAttribute("students",students);
        //studentListページへ。
        return "studentList"; //students.htmlに渡す
    }

    //登録ボタン押下時
    @PostMapping("/studentList")
    public String studentSave(@ModelAttribute Student s)
    {
        //DBに入力データを登録
        service.addStudent(s);
        return "redirect:/studentList";
    }
    //学生追加画面表示
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        //モデルに学生クラスを追加
        model.addAttribute("student"
                                ,new Student());
        //studentListページへ。
        return "addStudent"; //addStudent.htmlに渡す
    }
}
