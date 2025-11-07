package com.example.student.model;

public class Kamoku {
    //科目ID
    private Integer kamokuId;
    //科目名
    private String kamokuName;
    //学期
    private Integer gakki;
    
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

    //学期取得
    public Integer getGakki(){
        return gakki;
    }
    //学期設定
    public void setGakki(Integer gakki){
        this.gakki=gakki;
    }

}
