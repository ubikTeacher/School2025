package com.example.student.controller;

import com.example.student.model.Teacher;
import com.example.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class TeacherController {

    //サービスの宣言
    @Autowired
    private TeacherService service;

    //科目一覧ページ表示時
    @GetMapping("/teacherList")
    public String ShowTeacherListPage(Model model){
        //サービスにデータを取得してもらう
        List<Teacher> Teachers=service.getTeacher();
        //取得した科目情報をモデルにセット
        model.addAttribute("teachers",Teachers);
        //TeacherListページへ。
        return "teacherList"; //students.htmlに渡す
    }

    //科目一覧画面で、編集ボタンが押された時の処理
    @GetMapping("/teacherList/edit/{id}")
    public String ShowEditTeacherPage(@PathVariable int id,Model model)
    {
        //idから教員情報を取得
        Teacher s = service.getTeacherById(id);

        //編集画面に渡す
        //モデルに学生クラスを追加
        model.addAttribute("teacher",s);

        return "editTeacher";
    }

    //教員一覧画面で、削除ボタンが押された時の処理
    @PostMapping("/teacherList/delete/{id}")
    public String deleteTeacher(@PathVariable int id)
    {
        service.deleteTeacher(id);
        return "redirect:/teacherList";
    }

    //教員一覧画面で、新規登録ボタンを押された時の処理
    @GetMapping("/addTeacher")
    public String ShowAddStudentPage(Model model){
        //モデルに学生クラスを追加
        model.addAttribute("teacher"
                ,new Teacher());
        //TeacherListページへ。
        return "addTeacher"; //addTeacher.htmlに渡す
    }

    //教員情報登録ページで登録ボタンが押された時の処理
    @PostMapping("/addTeacher")
    public String addTeacher(@ModelAttribute Teacher s
            ,Model model)
    {
        try
        {
            //DBに入力データを登録
            service.addTeacher(s);
            return "redirect:/teacherList";
        }
        catch (DuplicateKeyException e)
        {
            //modelにエラー情報を登録
            model.addAttribute("errorMessage"
                    ,e.getMessage());
            //教員IDが重複していた場合
            return "addTeacher";
        }
    }

    //教員情報編集ページで更新ボタンが押された時の処理
    @PostMapping("/editTeacher")
    public String editTeacher(@ModelAttribute Teacher s)
    {
        //DBに入力データを登録
        service.editTeacher(s);
        return "redirect:/teacherList";
    }
}
