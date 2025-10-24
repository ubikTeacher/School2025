package com.example.student.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.service.StudentService;
import com.example.student.service.CourseService;
import com.example.student.model.Student;
import com.example.student.model.Course;
import java.util.List;

@Controller
public class StudentController {

    //サービスの宣言
    @Autowired
    private StudentService studentService;
    //コースのサービスの宣言
    @Autowired
    private CourseService courseService;

    //学生一覧ページ表示時
    @GetMapping("/studentList")
    public String ShowStudentListPage(Model model){
        //サービスにデータを取得してもらう
        List<Student> students= studentService.getStudents();
        //取得した学生情報をモデルにセット
        model.addAttribute("students",students);
        //studentListページへ。
        return "studentList"; //students.htmlに渡す
    }

    //学生一覧画面で、編集ボタンが押された時の処理
    @GetMapping("/studentList/edit/{id}")
    public String ShowEditStudentPage(@PathVariable int id,Model model)
    {
        //コースリストコンボを表示するために
        //コースリストを取得する
        List<Course> cList =courseService.getCourse();

        //取得したコースリストをモデルに登録
        model.addAttribute("courseList",cList);

        //idから学生情報を取得
        Student s = studentService.getStudentById(id);

        //編集画面に渡す
        //モデルに学生クラスを追加
        model.addAttribute("student",s);

        return "editStudent";
    }

    //学生一覧画面で、削除ボタンが押された時の処理
    @PostMapping("/studentList/delete/{id}")
    public String deleteStudent(@PathVariable int id)
    {
        studentService.deleteStudent(id);
        return "redirect:/studentList";
    }

    //学生一覧画面で、新規登録ボタンを押された時の処理
    @GetMapping("/addStudent")
    public String ShowAddStudentPage(Model model){

        //コースリストコンボを表示するために
        //コースリストを取得する
        List<Course> cList =courseService.getCourse();

        //取得したコースリストをモデルに登録
        model.addAttribute("courseList",cList);

        //モデルに学生クラスを追加
        model.addAttribute("student"
                                ,new Student());

        //studentListページへ。
        return "addStudent"; //addStudent.htmlに渡す
    }

    //学生情報登録ページで登録ボタンが押された時の処理
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Student s
                            ,Model model)
    {
        try {
            //DBに入力データを登録
            studentService.addStudent(s);
            return "redirect:/studentList";
        }
        catch (DuplicateKeyException e)
        {
            //modelにエラー情報を登録
            model.addAttribute("errorMessage"
                                ,e.getMessage());
            //学籍番号が重複していた場合
            return "addStudent";
        }
    }

    //学生情報編集ページで更新ボタンが押された時の処理
    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute Student s)
    {
        //DBに入力データを登録
        studentService.editStudent(s);
        return "redirect:/studentList";
    }
}
