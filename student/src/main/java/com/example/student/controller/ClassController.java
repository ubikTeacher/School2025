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
public class ClassController {

    //サービスの宣言
    @Autowired
    private KamokuService kamokuService;
    //サービスの宣言
    @Autowired
    private ClassService classService;

    //科目ごとの受講クラス選択ページ表示時
    @GetMapping("/classList/{kid}")
    public String ShowClassListPage(@PathVariable int kid,
                                    Model model){
        //①科目情報をモデルにセット
        //サービスにデータを取得してもらう
        Kamoku k=kamokuService.getKamokuById(kid);
        //取得した科目情報をモデルにセット
        model.addAttribute("kamoku",k);

        //②その科目のクラス情報をモデルにセット

        //classListページへ。
        return "classList";
    }
}
