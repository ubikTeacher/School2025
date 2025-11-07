package com.example.student.dao;

import com.example.student.model.Class;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassDAO {
    //DB接続情報
    private static final String URL
            = "jdbc:mysql://172.16.0.160:3306/School2025";
    private static final String USER
            ="ubkinfo";
    private static final String PASS
            ="3731040";

    //指定科目のクラス情報取得
    public List<Class> findAllBykamokuId(int kid)
    {
        List<Class> returnList= new ArrayList<>();
        String sql=	"select "
                + "   class.kamoku_id "
                + "  , kamoku.kamoku_name "
                + "  , class.course_id "
                + "  , course.course_name "
                + "  , class.gakunen "
                + "from class "
                + "left join course "
                + "  on class.course_id= course.course_id "
                + "left join kamoku "
                + "  on class.kamoku_id= kamoku.kamoku_id "
                + "where class.kamoku_id=?; ";


        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            stmt.setInt(1,kid);
            ResultSet rs=stmt.executeQuery();
            //とれた結果からコース情報のリストを作る
            while (rs.next()){
                Class s = new Class();

                s.setKamokuId(rs.getInt("kamoku_id"));
                s.setKamokuName(rs.getString("kamoku_name"));
                s.setCourseId(rs.getInt("course_id"));
                s.setCourseName(rs.getString("course_name"));
                s.setGakunen(rs.getInt("gakunen"));

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

    //指定された科目、コース、学年情報がDBに存在するかチェックします。
    //存在する場合はTrue,存在しない場合はFalseで返します。
    public boolean existsClass(int kId,int cId,int g)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM class "
                +  " WHERE kamoku_id=? "
                +  "   AND course_id=? "
                +  "   AND gakunen=? ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //③SQLを実行
            stmt.setInt(1,kId);//パラメータ設定
            stmt.setInt(2,cId);//パラメータ設定
            stmt.setInt(3,g);//パラメータ設定

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

    //指定された科目ID、コースID、学年のクラスデータを取得
    public Class getClassById(int kid,int cid,int g)
    {
        //①SQLを作成
        String sql=	"select "
                + "   class.kamoku_id "
                + "  , kamoku.kamoku_name "
                + "  , class.course_id "
                + "  , course.course_name "
                + "  , class.gakunen "
                + "from class "
                + "left join course "
                + "  on class.course_id= course.course_id "
                + "left join kamoku "
                + "  on class.kamoku_id= kamoku.kamoku_id "
                + "where class.kamoku_id=? "
                + "  and class.course_id=? "
                + "  and class.gakunen=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            //③SQLを実行して結果をStudentクラスに入れる
            stmt.setInt(1,kid);//パラメータ設定
            stmt.setInt(2,cid);//パラメータ設定
            stmt.setInt(3,g);//パラメータ設定
            ResultSet rs=stmt.executeQuery();

            //④情報をセットしたコース情報を返す
            while (rs.next()){
                Class s = new Class();
                s.setKamokuId(rs.getInt("kamoku_id"));
                s.setKamokuName(rs.getString("kamoku_name"));
                s.setCourseId(rs.getInt("course_id"));
                s.setCourseName(rs.getString("course_name"));
                s.setGakunen(rs.getInt("gakunen"));
                //セットした情報を返す
                return s;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new Class();
    }

    //クラスデータ新規登録
    public void addClass(Class s)
    {
        //①InsertのSQLを作る
        String sql;
        sql=	"INSERT "
                + "INTO class( "
                + "    kamoku_id "
                + "    , course_id "
                + "    , gakunen "
                + ") "
                + "VALUES (? , ? , ?  ); ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            //科目ID
            stmt.setInt(1,s.getKamokuId());
            //コースID
            stmt.setInt(2,s.getCourseId());
            //学年
            stmt.setInt(3,s.getGakunen());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //指定した科目、コース、学年から、クラスデータを削除
    public void deleteClass(int kid,int cid, int g)
    {
        //①InsertのSQLを作る
        String sql;
        sql="DELETE FROM class "
                + "WHERE kamoku_id=? "
                + "  AND course_id=? "
                + "  AND gakunen=? ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {

            stmt.setInt(1,kid);
            stmt.setInt(2,cid);
            stmt.setInt(3,g);

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}