package com.example.student.dao;

import com.example.student.model.Course;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDAO {
    //DB接続情報
    private static final String URL
            = "jdbc:mysql://172.16.0.160:3306/School2025";
    private static final String USER
            ="ubkinfo";
    private static final String PASS
            ="3731040";

    //全コース取得
    public List<Course> findAll(){
        List<Course> returnList= new ArrayList<>();
        String sql="SELECT * FROM course;";

        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery())
        {
            //とれた結果からコース情報のリストを作る
            while (rs.next()){
                Course s = new Course();
                s.setCourseId(rs.getInt("course_id"));
                s.setCourseName(rs.getString("course_name"));
                s.setMainTeacher(rs.getString("main_teacher"));
                s.setSubTeacher(rs.getString("sub_teacher"));

                //リストにコースデータ追加
                returnList.add(s);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return returnList; //学生情報のリストを戻す
    }
    //指定されたコースIDのコース情報がDBに存在するかチェックします。
    //存在する場合はTrue,存在しない場合はFalseで返します。
    public boolean existsCourse(int id)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM course WHERE course_id=?; ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //③SQLを実行
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
    //指定されたコースIDのコース情報を取得
    public Course getCourseById(int id)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM course WHERE course_id=?; ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            //③SQLを実行して結果をStudentクラスに入れる
            stmt.setInt(1,id);//パラメータ設定
            ResultSet rs=stmt.executeQuery();

            //④情報をセットしたコース情報を返す
            while (rs.next()){
                Course s = new Course();
                s.setCourseId(rs.getInt("course_id"));
                s.setCourseName(rs.getString("course_name"));
                s.setMainTeacher(rs.getString("main_teacher"));
                s.setSubTeacher(rs.getString("sub_teacher"));
                //セットした情報を返す
                return s;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new Course();
    }

    //コースデータ新規登録
    public void addCourse(Course s){
        //①InsertのSQLを作る
        String sql;
        sql=	"INSERT "
                + "INTO course( "
                + "    course_id "
                + "    , course_name "
                + "    , main_teacher "
                + "    , sub_teacher "
                + ") "
                + "VALUES (? , ? , ? , ? ); ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            //コースID
            stmt.setInt(1,s.getCourseId());
            //コース名
            stmt.setString(2,s.getCourseName());
            //主担任
            stmt.setString(3,s.getMainTeacher());
            //副担任
            stmt.setString(4, s.getSubTeacher());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //コースデータ更新
    public void editCourse(Course s){
        //① UpdateのSQLを作る
        String sql;
        sql="UPDATE course SET "
            + "   course_name=? "
            + " , main_teacher=? "
            + " , sub_teacher=? "
            + "WHERE course_id=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
           //コース名
            stmt.setString(1,s.getCourseName());
            //主担任
            stmt.setString(2,s.getMainTeacher());
            //副担任
            stmt.setString(3, s.getSubTeacher());
            //コースID
            stmt.setInt(4,s.getCourseId());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //コースデータ削除
    public void deleteCourse(int id){
        //①InsertのSQLを作る
        String sql;
        sql="DELETE FROM course "
                + "WHERE course_id=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //コースID
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