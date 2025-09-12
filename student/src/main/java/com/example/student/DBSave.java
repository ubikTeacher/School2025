package com.example.student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBSave {
    public static void main(String[] args) {
        // MySQL 接続情報
        String url
                = "jdbc:mysql://172.16.0.160:3306/School2025";
        String user = "ubkinfo";
        String password = "3731040";

        // 挿入するデータ
        String strSQL = "INSERT INTO students (student_id"
                        + ", student_name, student_seibetu) "
                        + "VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url
                                                , user, password);
             PreparedStatement stmt
                     = conn.prepareStatement(strSQL)) {
            // データの設定
            stmt.setInt(1, 1);           // 氏名
            stmt.setString(2, "田中太郎"); // 名前
            stmt.setInt(3, 20);          // 性別
            // SQL の実行
            int row = stmt.executeUpdate();
            System.out.println(row + " 行が挿入されました。");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}