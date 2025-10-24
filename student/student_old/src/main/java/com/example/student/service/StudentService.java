package com.example.student.service;

import com.example.student.dao.StudentDAO;
import com.example.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDAO dao;

    //全学生データ取得
    public List<Student> getStudents() {
        return dao.findAll();
    }
}
