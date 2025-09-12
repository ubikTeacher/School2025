package com.example.student.model;

public class Student {
    private int studentId;
    private String studentName;
    private int studentSeibetu;

    // デフォルトコンストラクタ
    public Student() {}
    // コンストラクタ
    public Student(int studentId, String studentName, int studentSeibetu) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSeibetu = studentSeibetu;
    }

    // Getter & Setter
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public int getStudentSeibetu() { return studentSeibetu; }
    public void setStudentSeibetu(int studentSeibetu) { this.studentSeibetu = studentSeibetu; }
}