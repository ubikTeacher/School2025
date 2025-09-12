package com.example.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(Model model) {
        // 自己紹介情報をセット
        model.addAttribute("name","高岸唯");
        model.addAttribute("hobby", "お散歩♪");
        model.addAttribute("seiza", "おとめ座です♪");

        return "index";  // resources/templates/index.html を返す
    }

    @PostMapping("/profile")
    public String profile(@RequestParam("txtName") String param1
                        ,@RequestParam("txtHobby") String param2
                        ,@RequestParam("cmbSeiza") String param3
                        ,@RequestParam("seibetu") String param4
                        ,@RequestParam("sports") String param5
                        ,Model model)
    {
        model.addAttribute("name",param1);
        model.addAttribute("hobby",param2);
        model.addAttribute("seiza",param3);
        model.addAttribute("seibetu",param4);
        model.addAttribute("sports",param5);
        return "profile";//htmlファイルの名前
    }
}
