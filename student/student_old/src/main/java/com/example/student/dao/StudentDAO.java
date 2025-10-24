package com.example.student.dao;

import org.springframework.stereotype.Repository;
import com.example.student.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//学生情報テーブル操作用クラス
@Repository
public class StudentDAO {
    private static final String URL
            = "jdbc:mysql://172.16.0.160:3306/School2025?useSSL=false&serverTimezone=UTC";
    private static final String USER
            = "ubkinfo";
    private static final String PASS
            = "3731040";

    //全学生情報を取得します
    public List<Student> findAll(){
        List<Student> returnList= new ArrayList<>();
        String sql="SELECT * FROM students;";

        //DB接続
        try(Connection conn
                    = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            //とれた結果から学生情報のリストを作る
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setStudentName(rs.getString("student_name"));
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

        return returnList;  //学生情報のリストを戻す
    }



    // UPDATE
    public void update(Student s) {
        String sql = "UPDATE students SET student_name=?, student_seibetu=? WHERE student_id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getStudentName());
            stmt.setInt(2, s.getStudentSeibetu());
            stmt.setInt(3, s.getStudentId());

            stmt.executeUpdate();
            System.out.println("学生情報を更新しました: " + s.getStudentName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE student_id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("学生を削除しました: " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}