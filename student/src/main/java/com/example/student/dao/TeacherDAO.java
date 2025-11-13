package com.example.student.dao;

import com.example.student.model.Teacher;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherDAO {
    //DB接続情報
    private static final String URL
            = "jdbc:mysql://172.16.0.160:3306/School2025";
    private static final String USER
            ="ubkinfo";
    private static final String PASS
            ="3731040";

    //全科目取得
    public List<Teacher> findAll(){
        List<Teacher> returnList= new ArrayList<>();
        String sql="SELECT * FROM teachers;";

        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery())
        {
            //とれた結果から科目情報のリストを作る
            while (rs.next()){
                Teacher s = new Teacher();
                s.setTeacherId(rs.getInt("teacher_id"));
                s.setTeacherName(rs.getString("teacher_name"));

                //リストに科目データ追加
                returnList.add(s);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return returnList; //学生情報のリストを戻す
    }
    //指定された科目IDの科目情報がDBに存在するかチェックします。
    //存在する場合はTrue,存在しない場合はFalseで返します。
    public boolean existsTeacher(int id)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM teachers WHERE teacher_id=?; ";
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
    //指定された科目IDの科目情報を取得
    public Teacher getTeacherById(int id)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM teachers WHERE teacher_id=?; ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //③SQLを実行して結果をStudentクラスに入れる
            stmt.setInt(1,id);//パラメータ設定
            ResultSet rs=stmt.executeQuery();

            //④情報をセットした科目情報を返す
            while (rs.next()){
                Teacher s = new Teacher();
                s.setTeacherId(rs.getInt("teacher_id"));
                s.setTeacherName(rs.getString("teacher_name"));
                //セットした情報を返す
                return s;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new Teacher();
    }

    //科目データ新規登録
    public void addTeacher(Teacher s){
        //①InsertのSQLを作る
        String sql;
        sql=	"INSERT "
                + "INTO teachers( "
                + "    teacher_id "
                + "    , teacher_name "
                + ") "
                + "VALUES ( ? , ? ); ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //科目ID
            stmt.setInt(1,s.getTeacherId());
            //科目名
            stmt.setString(2,s.getTeacherName());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //科目データ更新
    public void editTeacher(Teacher s){
        //① UpdateのSQLを作る
        String sql;
        sql="UPDATE teachers SET "
                + "   teacher_name=? "
                + "WHERE teacher_id=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //科目名
            stmt.setString(1,s.getTeacherName());
            //科目ID
            stmt.setInt(2,s.getTeacherId());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //科目データ削除
    public void deleteTeacher(int id){
        //①InsertのSQLを作る
        String sql;
        sql="DELETE FROM teachers "
                + "WHERE teacher_id=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //科目ID
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
