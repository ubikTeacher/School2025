package com.example.student.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.service.CourseService;
import com.example.student.model.Course;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class CourseController {

    //サービスの宣言
    @Autowired
    private CourseService service;

    //コース一覧ページ表示時
    @GetMapping("/courseList")
    public String ShowCourseListPage(Model model){
        //サービスにデータを取得してもらう
        List<Course> courses=service.getCourse();
        //取得したコース情報をモデルにセット
        model.addAttribute("courses",courses);
        //courseListページへ。
        return "courseList"; //students.htmlに渡す
    }

    //コース一覧画面で、編集ボタンが押された時の処理
    @GetMapping("/courseList/edit/{id}")
    public String ShowEditCoursePage(@PathVariable int id,Model model)
    {
        //idからコース情報を取得
        Course s = service.getCourseById(id);

        //編集画面に渡す
        //モデルに学生クラスを追加
        model.addAttribute("course",s);

        return "editCourse";
    }

    //コース一覧画面で、削除ボタンが押された時の処理
    @PostMapping("/courseList/delete/{id}")
    public String deleteCourse(@PathVariable int id)
    {
        service.deleteCourse(id);
        return "redirect:/courseList";
    }

    //コース一覧画面で、新規登録ボタンを押された時の処理
    @GetMapping("/addCourse")
    public String ShowAddStudentPage(Model model){
        //モデルに学生クラスを追加
        model.addAttribute("course"
                ,new Course());
        //courseListページへ。
        return "addCourse"; //addCourse.htmlに渡す
    }

    //コース情報登録ページで登録ボタンが押された時の処理
    @PostMapping("/addCourse")
    public String addCourse(@ModelAttribute Course s
                            ,Model model)
    {
        try
        {
            //DBに入力データを登録
            service.addCourse(s);
            return "redirect:/courseList";
        }
        catch (DuplicateKeyException e)
        {
            //modelにエラー情報を登録
            model.addAttribute("errorMessage"
                    ,e.getMessage());
            //コースIDが重複していた場合
            return "addCourse";
        }
    }

    //コース情報編集ページで更新ボタンが押された時の処理
    @PostMapping("/editCourse")
    public String editCourse(@ModelAttribute Course s)
    {
        //DBに入力データを登録
        service.editCourse(s);
        return "redirect:/courseList";
    }
}
