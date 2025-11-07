package com.example.student.service;


import com.example.student.dao.ClassDAO;
import com.example.student.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassDAO dao;

    //全クラスデータ取得
    public List<Class> getClasses(){
        return dao.findAll();
    }
    //クラスデータ新規登録
    public void addClass(Class s){
        //入力チェック追加
        //すでにその生徒情報が登録済みかのチェック
        if (dao.existsClass(s.getKamokuId(),s.getCourseId(),s.getGakunen())==true)
        {
            //自分でエラーを起こす
            throw new DuplicateKeyException("そのクラスIDはすでに登録されています");
        }
        dao.addClass(s);
    }

    //クラスデータ更新
    public void editKamoku(Class s){
        //dao.editKamoku(s);
    }

    //クラスデータ削除
    public void deleteKamoku(int id){
        dao.deleteClass(id);
    }
    //クラスデータをクラスIDから取得する
    public Class getClassById(int kid,int cid, int g)
    {
        //クラスIDからクラスデータをDBからとってきてもらう
        Class s =dao.getClassById(kid,cid,g);
        //取得した情報を返す
        return s;
    }
}
