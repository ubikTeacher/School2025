package com.example.student.service;

import com.example.student.dao.SeisekiDAO;
import com.example.student.model.Seiseki;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeisekiService {
    @Autowired
    private SeisekiDAO dao;

    //科目IDから対象の成績データ取得
    public List<Seiseki> getSeisekiByKamokuId(int kid){
        return dao.findAllByKamokuId(kid);
    }
    //成績データ新規登録
    public void addSeiseki(Seiseki s){
        //入力チェック追加
        //すでにその生徒情報が登録済みかのチェック
        if (dao.existsSeiseki(s.getKamokuId()
                        ,s.getStudentId()))
        {
            //自分でエラーを起こす
            throw new DuplicateKeyException("その成績データはすでに登録されています");
        }
        dao.addSeiseki(s);
    }

    //成績データ削除
    public void deleteSeiseki(int kId,int sId){
        dao.deleteSeiseki(kId,sId);
    }

}
