package com.example.student.service;

import com.example.student.dao.StudentDAO;
import com.example.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDAO dao;

    //全学生データ取得
    public List<Student> getStudents(){
        return dao.findAll();
    }
    //学生データ新規登録
    public void addStudent(Student s){
        //入力チェック追加
        //すでにその生徒情報が登録済みかのチェック
        if (dao.existsStudent(s.getStudentId())==true)
        {
            //自分でエラーを起こす
            throw new DuplicateKeyException("その学籍番号はすでに登録されています");
        }
        dao.addStudent(s);
    }

    //学生データ更新
    public void editStudent(Student s){
        dao.editStudent(s);
    }

    //学生データ削除
    public void deleteStudent(int id){
        dao.deleteStudent(id);
    }
    //学生データを学籍番号から取得する
    public Student getStudentById(int id)
    {
        //学籍番号から学生データをDBからとってきてもらう
        Student s =dao.getStudentById(id);
        //取得した情報を返す
        return s;
    }
}
