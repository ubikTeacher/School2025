package com.example.student.model;

public class Class {
    //科目ID
    private Integer kamokuId;
    //科目名
    private String kamokuName;
    //コースID
    private Integer courseId;
    //コース名
    private String courseName;
    //学年
    private Integer gakunen;

    //科目ID取得
    public Integer getKamokuId(){
        return kamokuId;
    }
    //科目ID設定
    public void setKamokuId(Integer kamokuId){
        this.kamokuId=kamokuId;
    }

    //科目名取得
    public String getKamokuName(){
        return kamokuName;
    }
    //科目名設定
    public void setKamokuName(String kamokuName){
        this.kamokuName=kamokuName;
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

    //学年取得
    public Integer getGakunen(){
        return gakunen;
    }
    //学年設定
    public void setGakunen(Integer gakunen){
        this.gakunen=gakunen;
    }


}
