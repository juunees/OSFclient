package pro.junes.osfightclient;

import android.os.AsyncTask;

import java.sql.*;

public class  PostgreSqlJDBC  extends AsyncTask<Void, Void, Void> {

    static String cadenaConexion = "jdbc:postgresql://localhost:5432/OSFight?" + "user=postgres&password=postgres";
    static String respuestaSql= "vacia";


    public String getRespuestaSql (){
        execute();
        return respuestaSql;
    }

    @Override
    public Void doInBackground(Void... params) {

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        try {
            Class.forName("org.postgresql.Driver");
             System.out.println(">>Driver work correct!!");

            conexion = DriverManager.getConnection(cadenaConexion);

            System.out.println("Succesful connection");

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM activities";
            resultado = sentencia.executeQuery(consultaSQL);
            respuestaSql = "";
            while (resultado.next()) {
                int id = resultado.getInt("IdActivity");
                String Nombre = resultado.getString("NameActivity");
                respuestaSql = respuestaSql + id + " | " + Nombre +  "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.err.println("Error: Cant connect!");
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }
        }
        System.err.println("----- PostgreSQL query ends correctly!-----");
        return null;
    }
}