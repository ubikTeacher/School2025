package com.example.student.model;

//学生テーブル列情報
public class Student {
    //学籍番号
    private int studentId;
    //氏名
    private String studentName;
    //性別
    private int studentSeibetu;
    //コースID
    private int courseId;

    //学籍番号取得
    public int getStudentId(){
        return this.studentId;
    }
    //学籍番号設定
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    //氏名取得
    public String getStudentName(){
        return this.studentName;
    }
    //氏名設定
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    //性別取得
    public int getStudentSeibetu() { return studentSeibetu; }
    //性別設定
    public void setStudentSeibetu(int studentSeibetu) { this.studentSeibetu = studentSeibetu; }
    //コース取得
    public int getCourseId() { return this.courseId; }
    //コース設定
    public void setCourseId(int courseId) { this.courseId = courseId; }

}
