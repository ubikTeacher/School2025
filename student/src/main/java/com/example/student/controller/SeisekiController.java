package com.example.student.controller;

import com.example.student.model.Seiseki;
import com.example.student.service.SeisekiService;
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
public class SeisekiController {

    //サービスの宣言
    @Autowired
    private SeisekiService service;

    //成績一覧ページ表示時
    @GetMapping("/seisekiList/{id}")
    public String ShowSeisekiListPage(@PathVariable int id
                                    ,Model model){
        //サービスにデータを取得してもらう
        List<Seiseki> seiseki=service.getSeisekiByKamokuId(id);
        //取得した科目情報をモデルにセット
        model.addAttribute("seisekiList",seiseki);
        //seisekiListページへ
        return "seisekiList";
    }
}
