package com.example.student.service;


import com.example.student.dao.KamokuDAO;
import com.example.student.model.Kamoku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KamokuService {
    @Autowired
    private KamokuDAO dao;

    //全科目データ取得
    public List<Kamoku> getKamoku(){
        return dao.findAll();
    }
    //Kamokuデータ新規登録
    public void addKamoku(Kamoku s){
        //入力チェック追加
        //すでにその生徒情報が登録済みかのチェック
        if (dao.existsKamoku(s.getKamokuId())==true)
        {
            //自分でエラーを起こす
            throw new DuplicateKeyException("その科目IDはすでに登録されています");
        }
        dao.addKamoku(s);
    }

    //科目データ更新
    public void editKamoku(Kamoku s){
        dao.editKamoku(s);
    }

    //科目データ削除
    public void deleteKamoku(int id){
        dao.deleteKamoku(id);
    }
    //科目データを科目IDから取得する
    public Kamoku getKamokuById(int id)
    {
        //科目IDから科目データをDBからとってきてもらう
        Kamoku s =dao.getKamokuById(id);
        //取得した情報を返す
        return s;
    }
}
