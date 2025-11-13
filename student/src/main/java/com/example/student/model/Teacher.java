package com.example.student.model;

public class Teacher {
    //科目ID
    private Integer teacherId;
    //科目名
    private String teacherName;
    
    //科目ID取得
    public Integer getTeacherId(){
        return teacherId;
    }
    //科目ID設定
    public void setTeacherId(Integer teacherId){
        this.teacherId=teacherId;
    }

    //科目名取得
    public String getTeacherName(){
        return teacherName;
    }
    //科目名設定
    public void setTeacherName(String teacherName){
        this.teacherName=teacherName;
    }

}
