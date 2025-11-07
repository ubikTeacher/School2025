package com.example.student.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.service.KamokuService;
import com.example.student.model.Kamoku;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class KamokuController {

    //サービスの宣言
    @Autowired
    private KamokuService service;

    //科目一覧ページ表示時
    @GetMapping("/kamokuList")
    public String ShowKamokuListPage(Model model){
        //サービスにデータを取得してもらう
        List<Kamoku> Kamokus=service.getKamoku();
        //取得した科目情報をモデルにセット
        model.addAttribute("kamoku",Kamokus);
        //KamokuListページへ。
        return "kamokuList"; //students.htmlに渡す
    }

    //科目一覧画面で、編集ボタンが押された時の処理
    @GetMapping("/kamokuList/edit/{id}")
    public String ShowEditKamokuPage(@PathVariable int id,Model model)
    {
        //idから科目情報を取得
        Kamoku s = service.getKamokuById(id);

        //編集画面に渡す
        //モデルに学生クラスを追加
        model.addAttribute("kamoku",s);

        return "editKamoku";
    }

    //科目一覧画面で、削除ボタンが押された時の処理
    @PostMapping("/kamokuList/delete/{id}")
    public String deleteKamoku(@PathVariable int id)
    {
        service.deleteKamoku(id);
        return "redirect:/kamokuList";
    }

    //科目一覧画面で、新規登録ボタンを押された時の処理
    @GetMapping("/addKamoku")
    public String ShowAddStudentPage(Model model){
        //モデルに学生クラスを追加
        model.addAttribute("kamoku"
                ,new Kamoku());
        //KamokuListページへ。
        return "addKamoku"; //addKamoku.htmlに渡す
    }

    //科目情報登録ページで登録ボタンが押された時の処理
    @PostMapping("/addKamoku")
    public String addKamoku(@ModelAttribute Kamoku s
            ,Model model)
    {
        try
        {
            //DBに入力データを登録
            service.addKamoku(s);
            return "redirect:/kamokuList";
        }
        catch (DuplicateKeyException e)
        {
            //modelにエラー情報を登録
            model.addAttribute("errorMessage"
                    ,e.getMessage());
            //科目IDが重複していた場合
            return "addKamoku";
        }
    }

    //科目情報編集ページで更新ボタンが押された時の処理
    @PostMapping("/editKamoku")
    public String editKamoku(@ModelAttribute Kamoku s)
    {
        //DBに入力データを登録
        service.editKamoku(s);
        return "redirect:/kamokuList";
    }
}
