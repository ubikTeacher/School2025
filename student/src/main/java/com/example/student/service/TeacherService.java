package com.example.student.service;


import com.example.student.dao.TeacherDAO;
import com.example.student.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherDAO dao;

    //全科目データ取得
    public List<Teacher> getTeacher(){
        return dao.findAll();
    }
    //Teacherデータ新規登録
    public void addTeacher(Teacher s){
        //入力チェック追加
        //すでにその生徒情報が登録済みかのチェック
        if (dao.existsTeacher(s.getTeacherId())==true)
        {
            //自分でエラーを起こす
            throw new DuplicateKeyException("その教員IDはすでに登録されています");
        }
        dao.addTeacher(s);
    }

    //教員データ更新
    public void editTeacher(Teacher s){
        dao.editTeacher(s);
    }

    //教員データ削除
    public void deleteTeacher(int id){
        dao.deleteTeacher(id);
    }
    //教員データを教員IDから取得する
    public Teacher getTeacherById(int id)
    {
        //教員IDから教員データをDBからとってきてもらう
        Teacher s =dao.getTeacherById(id);
        //取得した情報を返す
        return s;
    }
}
