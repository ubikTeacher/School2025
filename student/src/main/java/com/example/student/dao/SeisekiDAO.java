package com.example.student.dao;

import com.example.student.model.Seiseki;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeisekiDAO {
    //DB接続情報
    private static final String URL
            = "jdbc:mysql://172.16.0.160:3306/School2025";
    private static final String USER
            ="ubkinfo";
    private static final String PASS
            ="3731040";

    //指定科目IDのデータを取得
    public List<Seiseki> findAllByKamokuId(int kid)
    {
        List<Seiseki> returnList= new ArrayList<>();
        String sql=	"select "
                + "   st.student_id "
                + "  ,st.student_name "
                + "  ,se.kamoku_id "
                + "  ,se.midterm_score "
                + "  ,se.final_score "
                + "  ,se.final_point "
                + "from students st "
                + "left join seiseki se "
                + "  on st.student_id=se.student_id "
                + " and se.kamoku_id=?; ";


        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);)
        {//③SQLを実行

            stmt.setInt(1,kid);//パラメータ設定
            ResultSet rs=stmt.executeQuery();

            //とれた結果からコース情報のリストを作る
            while (rs.next()){
                Seiseki s = new Seiseki();
                s.setKamokuId(rs.getInt("kamoku_id"));
                s.setStudentId(rs.getInt("student_id"));
                s.setStudentName(rs.getString("student_name"));
                s.setMidtermScore(rs.getInt("midterm_score"));
                s.setFinalScore(rs.getInt("final_score"));
                s.setFinalPoint(rs.getInt("final_point"));

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

    //指定された科目、生徒情報がDBに存在するかチェックします。
    //存在する場合はTrue,存在しない場合はFalseで返します。
    public boolean existsSeiseki(int kId,int sId)
    {
        //①SQLを作成
        String sql=	"SELECT * FROM seiseki WHERE kamoku_id=? and student_id=?;";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //③SQLを実行
            stmt.setInt(1,kId);//パラメータ設定
            stmt.setInt(2,sId);//パラメータ設定

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

    //成績データ新規登録
    public void addSeiseki(Seiseki s)
    {
        //①InsertのSQLを作る
        String sql;
        sql=	"INSERT "
                + "INTO seiseki( "
                + "    kamoku_id "
                + "    , student_id "
                + "    , midterm_score "
                + "    , final_score "
                + "    , final_point "
                + ") "
                + "VALUES (? , ? , ? , ?, ? ); ";
        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            //科目ID
            stmt.setInt(1,s.getKamokuId());
            //コースID
            stmt.setInt(2,s.getStudentId());
            //中間点数
            stmt.setInt(3,s.getMidtermScore());
            //期末点数
            stmt.setInt(3,s.getFinalScore());
            //最終評価
            stmt.setInt(3,s.getFinalPoint());

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //成績データ削除
    public void deleteSeiseki(int kId, int sId)
    {
        //①InsertのSQLを作る
        String sql;
        sql="DELETE FROM seiseki "
                + "WHERE kamoku_id=? "
                + " AND student_id=?; ";

        //②DBに接続
        try(Connection conn
                    = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            //科目ID
            stmt.setInt(1,kId);
            //学籍番号
            stmt.setInt(2,sId);

            //③SQLを実行
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}