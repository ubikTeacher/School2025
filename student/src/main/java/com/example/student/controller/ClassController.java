package com.example.student.controller;

import com.example.student.model.Class;
import com.example.student.model.Kamoku;
import com.example.student.model.Course;
import com.example.student.service.ClassService;
import com.example.student.service.KamokuService;
import com.example.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class ClassController {

    //サービスの宣言
    @Autowired
    private ClassService classService;
    //サービスの宣言
    @Autowired
    private KamokuService kamokuService;
    //サービスの宣言
    @Autowired
    private CourseService courseService;

    //科目一覧ページ表示時
    @GetMapping("/classList/{id}")
    public String ShowClassListPage(@PathVariable int id
                                    ,Model model){

        //パラメータの科目IDから科目情報取得
        Kamoku kamoku=kamokuService.getKamokuById(id);

        //サービスに対象科目のクラスデータを取得してもらう
        List<Class> classes=classService.getClassByKamokuId(id);

        //サービスにコース情報を取ってきてもらう
        List<Course> clist=courseService.getCourse();

        //取得した科目情報をモデルにセット
        model.addAttribute("kamoku",kamoku);
        //取得したクラス情報をモデルにセット
        model.addAttribute("classlist",classes);
        //取得したコース情報をモデルにセット
        model.addAttribute("courseList",clist);
        //新規追加用
       Class classNew = new Class();
       classNew.setKamokuId(id);
        model.addAttribute("classNew",classNew);

        //コースリストコンボを表示するために
        //コースリストを取得する
        List<Course> cList =courseService.getCourse();

        //取得したコースリストをモデルに登録
        model.addAttribute("courseList",cList);

        //classListページへ。
        return "classList";
    }
//
//    //クラス一覧画面で、削除ボタンが押された時の処理
//    @PostMapping("/classList/delete/{kid}/{cid}/{g}")
//    public String deleteClass(@PathVariable int kid
//                                ,@PathVariable int cid
//                                ,@PathVariable int g
//                                ,Model model)
//    {
//        classService.deleteClass(kid,cid,g);
//        return "redirect:/classList/"+kid;
//    }
//
//    //クラス一覧画面で、追加ボタンが押された時の処理
//    @PostMapping("/classList/add")
//    public String addClass(@ModelAttribute Class c
//                            , Model model
//                            , RedirectAttributes redirectAttributes)
//    {
//        try
//        {
//            //DBに入力データを登録
//            classService.addClass(c);
//            return "redirect:/classList/" + c.getKamokuId();
//        }
//        catch (DuplicateKeyException e) {
//            //modelにエラー情報を登録
//            redirectAttributes.addFlashAttribute("errorMessage", "同じクラスはすでに登録されています。");
//
//            // 🔽 ここで再描画に必要なデータを再取得
//            model.addAttribute("kamoku", kamokuService.getKamokuById(c.getKamokuId()));
//            model.addAttribute("classlist", classService.getClasses(c.getKamokuId()));
//            model.addAttribute("courseList", courseService.getCourse());
//            model.addAttribute("classNew", new Class()); // 入力用オブジェクト
//
//            return "redirect:/classList/" + c.getKamokuId();
//        }
//    }
}
