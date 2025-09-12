package com.example.student.dao;

import org.springframework.stereotype.Repository;
import com.example.student.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAO {
    private static final String URL = "jdbc:mysql://172.16.0.160:3306/School2025?useSSL=false&serverTimezone=UTC";
    private static final String USER = "ubkinfo";
    private static final String PASS = "3731040";

    // INSERT
    public void insert(Student s) {
        String sql = "INSERT INTO students (student_id, student_name, student_seibetu) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, s.getStudentId());
            stmt.setString(2, s.getStudentName());
            stmt.setInt(3, s.getStudentSeibetu());

            stmt.executeUpdate();
            System.out.println("学生を追加しました: " + s.getStudentName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT 全件取得
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setStudentName(rs.getString("student_name"));
                s.setStudentSeibetu(rs.getInt("student_seibetu"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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