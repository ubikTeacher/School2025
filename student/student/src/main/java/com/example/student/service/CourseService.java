package com.example.student.service;

import com.example.student.dao.CourseDAO;
import com.example.student.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDAO dao;

    //全コースデータ取得
    public List<Course> getCourse(){
        return dao.findAll();
    }
    //Courseデータ新規登録
    public void addCourse(Course s){
        //入力チェック追加
        //すでにその生徒情報が登録済みかのチェック
        if (dao.existsCourse(s.getCourseId())==true)
        {
            //自分でエラーを起こす
            throw new DuplicateKeyException("そのコースIDはすでに登録されています");
        }
        dao.addCourse(s);
    }

    //コースデータ更新
    public void editCourse(Course s){
        dao.editCourse(s);
    }

    //コースデータ削除
    public void deleteCourse(int id){
        dao.deleteCourse(id);
    }
    //コースデータをコースIDから取得する
    public Course getCourseById(int id)
    {
        //コースIDからコースデータをDBからとってきてもらう
        Course s =dao.getCourseById(id);
        //取得した情報を返す
        return s;
    }
}
