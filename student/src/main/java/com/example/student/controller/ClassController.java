package com.example.student.controller;

import com.example.student.model.Class;
import com.example.student.model.Kamoku;
import com.example.student.model.Course;
import com.example.student.service.ClassService;
import com.example.student.service.KamokuService;
import com.example.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClassController {

    //サービスの宣言
    @Autowired
    private ClassService classService;
    //サービスの宣言
    @Autowired
    private CourseService courseService;

    //サービスの宣言
    @Autowired
    private KamokuService kamokuService;

    //科目一覧ページ表示時
    @GetMapping("/classList/{id}")
    public String ShowClassListPage(@PathVariable int id,Model model){

        Kamoku kamoku=kamokuService.getKamokuById(id);
        //サービスにデータを取得してもらう
        List<Class> classes=classService.getClasses();


        model.addAttribute("kamoku",kamoku);
        //取得した科目情報をモデルにセット
        model.addAttribute("class",classes);
        //KamokuListページへ。
        return "classList"; //students.htmlに渡す
    }

    //科目一覧画面で、編集ボタンが押された時の処理
    @GetMapping("/classList/edit/{kid}/{cid}/{g}")
    public String ShowEditKamokuPage(@PathVariable int kid
                                    ,@PathVariable int cid
                                    ,@PathVariable int g
                                    ,Model model)
    {
        List<Course>course=courseService.getCourse();
        model.addAttribute("courseList",course);

        //idから科目情報を取得
        Class s = classService.getClassById(kid,cid,g);

        //編集画面に渡す
        //モデルに学生クラスを追加
        model.addAttribute("class",s);

        return "editClass";
    }
}
