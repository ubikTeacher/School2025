package com.example.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    @GetMapping("/profile")
    public String index(Model model){
        //自己紹介情報をセット
        model.addAttribute("name","布村　光");
        model.addAttribute("hobby","お散歩♪");
        model.addAttribute("birth","2005年12月12日");
        model.addAttribute("ed","ユービック情報専門学校");
        model.addAttribute("ex","よろしくお願いします。");
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model){
        //自己紹介情報をセット

        return "test";
    }

    @PostMapping("/profile")
    public String profile(@RequestParam("txtname")String param1,
                          @RequestParam("txthobby")String param2,
                          @RequestParam("txtbirth")String param3,
                          @RequestParam("txted")String param4,
                          @RequestParam("txtex")String param5
                          ,Model model)
    {
        model.addAttribute("name",param1);
        model.addAttribute("hobby",param2);
        model.addAttribute("birth",param3);
        model.addAttribute("ed",param4);
        model.addAttribute("ex",param5);
        return "profile"; //htmlファイルの名前
    }

}
