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

    //指定科目IDのデータを取得
    public List<Class> findAllByKamokuId(int kid)
    {
        List<Class> returnList= new ArrayList<>();
        String sql=	"select "
                + "     cl.kamoku_id "
                + "    ,k.kamoku_name "
                + "    ,cl.course_id "
                + "    ,c.course_name "
                + "    ,cl.gakunen "
                + "from School2025.class cl "
                + " left join School2025.kamoku k "
                + "   on cl.kamoku_Id=k.kamoku_id "
                + " left join School2025.course c "
                + "   on cl.course_id=c.course_id "
                + "where cl.kamoku_Id=? ";

        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);)
        {//③SQLを実行

            stmt.setInt(1,kid);//パラメータ設定
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

    //指定された科目、コース、学年、担当情報がDBに存在するかチェックします。
    //存在する場合はTrue,存在しない場合はFalseで返します。
    public boolean existsClass(int kamokuId,int courseId,int gakunen)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM class WHERE kamoku_id=? and course_id=? and gakunen=? ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //③SQLを実行
            stmt.setInt(1,kamokuId);//パラメータ設定
            stmt.setInt(2,courseId);//パラメータ設定
            stmt.setInt(3,gakunen);//パラメータ設定

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

    //指定された科目IDのコース情報を取得
    public Class getClassById(int kid,int cid,int g)
    {
        //①SQLを作成
        String sql=	"select "
                + "     cl.kamoku_id "
                + "    ,k.kamoku_name "
                + "    ,cl.course_id "
                + "    ,c.course_name "
                + "    ,cl.gakunen "
                + "from School2025.class cl "
                + " left join School2025.kamoku k "
                + "   on cl.kamoku_Id=k.kamoku_id "
                + " left join School2025.course c "
                + "   on cl.course_id=c.course_id "
                + " where cl.kamoku_id=? ";
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

    //クラスデータ削除
    public void deleteClass(int kid, int cid, int g)
    {
        //①InsertのSQLを作る
        String sql;
        sql="DELETE FROM class "
                + "WHERE kamoku_id=? "
                + " AND course_id=? "
                + " AND gakunen=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //科目ID
            stmt.setInt(1,kid);
            //コースID
            stmt.setInt(2,cid);
            //学年
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