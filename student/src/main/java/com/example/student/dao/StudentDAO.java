package com.example.student.dao;

import org.springframework.stereotype.Repository;
import com.example.student.model.Student;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAO {
    private static final String URL
            = "jdbc:mysql://172.16.0.160:3306/School2025";
    private static final String USER
            ="ubkinfo";
    private static final String PASS
            ="3731040";

    //全学生
    public List<Student> findAll(){
        List<Student> returnList= new ArrayList<>();
        String sql="SELECT * FROM students;";

        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery())
        {
            //とれた結果から学生情報のリストを作る
            while (rs.next()){
                Student s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setStudentName(rs.getString("student_Name"));
                s.setStudentSeibetu(rs.getInt("student_seibetu"));
                s.setCourseId(rs.getInt("course_id"));
                //リストに学生データ追加
                returnList.add(s);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return returnList; //学生情報のリストを戻す
    }

}