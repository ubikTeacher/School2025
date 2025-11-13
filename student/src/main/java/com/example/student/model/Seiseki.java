package com.example.student.model;

public class Seiseki {
    //科目ID
    private Integer kamokuId;
    //科目名
    private String kamokuName;
    //学籍番号
    private Integer studentId;
    //学生氏名
    private String studentName;
    //中間試験点数
    private Integer midtermScore;
    //期末試験点数
    private Integer finalScore;
    //最終評価
    private Integer finalPoint;

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

    //中間試験点数取得
    public Integer getMidtermScore(){
        return this.midtermScore;
    }
    //中間試験点数設定
    public void setMidtermScore(Integer midtermScore){
        this.midtermScore=midtermScore;
    }

    //期末試験点数取得
    public Integer getFinalScore(){
        return this.finalScore;
    }
    //期末試験点数設定
    public void setFinalScore(Integer finalScore){
        this.finalScore=finalScore;
    }

    //最終評価取得
    public Integer getFinalPoint(){
        return this.finalPoint;
    }
    //最終評価設定
    public void setFinalPoint(Integer finalPoint){
        this.finalPoint=finalPoint;
    }
}
