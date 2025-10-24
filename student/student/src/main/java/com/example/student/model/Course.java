package com.example.student.model;

//コーステーブル列情報
public class Course {
    //コースID
    private Integer courseId;
    //コース名
    private String courseName;
    //主担任
    private String mainTeacher;
    //副担任
    private String subTeacher;

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

    //主担任取得
    public String getMainTeacher(){
        return mainTeacher;
    }
    //主担任設定
    public void setMainTeacher(String mainTeacher){
        this.mainTeacher=mainTeacher;
    }

    //副担任取得
    public String getSubTeacher(){return subTeacher;}
    //副担任設定
    public void setSubTeacher(String subTeacher){
        this.subTeacher=subTeacher;
    }

}
