package com.example.student.controller;

import com.example.student.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    //TOPページ表示時
    @GetMapping("/")
    public String ShowTopPage(Model model){

        //indexページへ。
        return "index";

    }
}
