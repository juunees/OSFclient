package pro.junes.osfightclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by junes on 29.03.17.
 */
public class StatisticActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);

        System.out.println(">>waiting");

        PostgreSqlJDBC sqlJDBC = new PostgreSqlJDBC();
        sqlJDBC.getRespuestaSql();

    /*   PostgreSqlJDBC postgreSql = new PostgreSqlJDBC();
        postgreSql.getRespuestaSql();*/

    }







   // private class FetchSQL extends AsyncTask<Void,Void,String> {
   /*public class FetchSQL  {

        protected String done() {
            String retval = "";
            try {
                Class.forName("org.postgresql.Driver");
                System.out.println(">>>>> DRIVER !! " );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                retval = e.toString();
            }
          //  URL_DATABASE = "jdbc:postgresql://localhost:5432/";
            String url = "jdbc:postgresql://localhost:5432/";//OSFight?user=postgres&password=postgres";
            Connection conn;
            try {
                DriverManager.setLoginTimeout(5);
              //  URL_DATABASE + this.db_name, this.db_user, this.db_password
                conn = DriverManager.getConnection(url+"OSFight","postgres","postgres");
               // Log.i("Connect to DB", String.valueOf(conn));
                System.out.print("Connect to DB");
                Statement st = conn.createStatement();
                String sql;

                sql = "SELECT user_id from game where id = 1";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()) {
                    retval = rs.getString(1);
                }
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                retval = e.toString();
            }
            return retval;
        }

    }*/

}
