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
        String sql="select \n" +
                "   students.* \n" +
                "  ,course.course_name\n" +
                "from students\n" +
                " left join course\n" +
                "   on students.course_id=course.course_id;";

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
                s.setCourseName(rs.getString("course_name"));
                s.setGakunen(rs.getInt("gakunen"));
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

    //指定された学籍番号の学生情報がDBに存在するかチェックします。
    //存在する場合はTrue,存在しない場合はFalseで返します。
    public boolean existsStudent(int id)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM students WHERE student_id=?; ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //③SQLを実行して結果をStudentクラスに入れる
            stmt.setInt(1,id);//パラメータ設定

            ResultSet rs=stmt.executeQuery();

            //④あったらTrueを返します
            if (rs.next()){
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        //⑤存在していないのでfalseを返す
        return false;
    }

    //指定された学籍番号の学生情報を取得
    public Student getStudentById(int id)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM students WHERE student_id=?; ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            //③SQLを実行して結果をStudentクラスに入れる
            stmt.setInt(1,id);//パラメータ設定
            ResultSet rs=stmt.executeQuery();

            //④情報をセットしたStudentを返す
            while (rs.next()){
                Student s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setStudentName(rs.getString("student_Name"));
                s.setStudentSeibetu(rs.getInt("student_seibetu"));
                s.setCourseId(rs.getInt("course_id"));
                s.setGakunen(rs.getInt("gakunen"));
                //セットした情報を返す
                return s;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new Student();
    }

    //学生データ新規登録
    public void addStudent(Student s){
        //①InsertのSQLを作る
        String sql;
        sql=	"INSERT "
                + "INTO students( "
                + "      student_id "
                + "    , student_name "
                + "    , student_seibetu "
                + "    , course_id "
                + "    , gakunen "
                + ") "
                + "VALUES (? , ? , ? , ? , ? ); ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            //学籍番号
            stmt.setInt(1,s.getStudentId());
            //氏名
            stmt.setString(2,s.getStudentName());
            //性別
            stmt.setInt(3,s.getStudentSeibetu());
            //コースID
            stmt.setInt(4, s.getCourseId());
            //学年
            stmt.setInt(5, s.getGakunen());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //学生データ更新
    public void editStudent(Student s){
        //① UpdateのSQLを作る
        String sql;
        sql="UPDATE students SET "
            + "   student_name=? "
            + " , student_seibetu=? "
            + " , course_id=? "
            + " , gakunen=? "
            + "WHERE student_id=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
           //氏名
            stmt.setString(1, s.getStudentName());
            //性別
            stmt.setInt(2, s.getStudentSeibetu());
            //コースID
            stmt.setInt(3, s.getCourseId());
            //学年
            stmt.setInt(4, s.getGakunen());
            //学籍番号
            stmt.setInt(5, s.getStudentId());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //学生データ削除
    public void deleteStudent(int id){
        //①InsertのSQLを作る
        String sql;
        sql="DELETE FROM students "
                + "WHERE student_id=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //学籍番号
            stmt.setInt(1,id);

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}