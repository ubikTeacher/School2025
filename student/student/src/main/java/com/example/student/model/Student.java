package com.example.student.model;

//学生テーブル列情報
public class Student {
    //学籍番号
    private Integer studentId;
    //氏名
    private String studentName;
    //性別
    private Integer studentSeibetu;
    //コースID
    private Integer courseId;
    //コース名
    private String courseName;

    //学籍番号取得
    public Integer getStudentId(){
        return studentId;
    }
    //学籍番号設定
    public void setStudentId(Integer studentId){
        this.studentId=studentId;
    }

    //氏名取得
    public String getStudentName(){
        return studentName;
    }
    //氏名設定
    public void setStudentName(String studentName){
        this.studentName=studentName;
    }

    //性別取得
    public Integer getStudentSeibetu(){
        return studentSeibetu;
    }
    //性別設定
    public void setStudentSeibetu(Integer studentSeibetu){
        this.studentSeibetu=studentSeibetu;
    }

    //コースID取得
    public Integer getCourseId(){
        return courseId;
    }
    //コースID設定
    public void setCourseId(Integer courseId){
        this.courseId=courseId;
    }

    //コース名取得
    public String getCourseName(){
        return courseName;
    }
    //コース名設定
    public void setCourseName(String courseName){
        this.courseName=courseName;
    }

}
