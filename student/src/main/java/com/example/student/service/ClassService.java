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

    //科目IDから対象のコース・学年データ取得
    public List<Class> getClassByKamokuId(int kid){
        return dao.findAllByKamokuId(kid);
    }
    //クラスデータ新規登録
    public void addClass(Class s){
        //入力チェック追加
        //すでにその生徒情報が登録済みかのチェック
        if (dao.existsClass(s.getKamokuId()
                        ,s.getCourseId()
                        ,s.getGakunen()))
        {
            //自分でエラーを起こす
            throw new DuplicateKeyException("そのコースと学年はすでに登録されています");
        }
        dao.addClass(s);
    }

    //クラスデータ削除
    public void deleteClass(int kid,int cid, int g){
        dao.deleteClass(kid,cid,g);
    }

    //クラスデータを科目ID、コースID,学年から取得する
    public Class getClassById(int kid,int cid, int g)
    {
        //科目ID、コースID,学年からクラスデータをDBからとってきてもらう
        Class s =dao.getClassById(kid,cid,g);
        //取得した情報を返す
        return s;
    }
}
