package pro.junes.osfightclient;

/**
 * Created by junes on 22.04.17.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;


public class Client extends AsyncTask<Object, Object, String> {


    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    Socket socket;
    InputStream sin;
    OutputStream sout;
    String who_i_am = "null";



    Client(String addr, int port)  {
        dstAddress = addr;
        dstPort = port;
    }



    public void sendMsg(String msg){

        DataInputStream in = new DataInputStream(sin);;
        DataOutputStream out =  new DataOutputStream(sout);

        String answer= String.valueOf(msg);

        try {
            System.out.println("Sending >> " + answer + " to the server");
            out.writeUTF(answer);
            out.flush(); // заставляем поток закончить передачу данных.
         //  String fromserver = in.readUTF();// ждем пока сервер отошлет строку текста.
          //  System.out.println(">> Server sent this :  " + fromserver);

        } catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


    }


    public int[] getOpponentspet(int count){

        int [] step = new int[2];

        int x_diff = 1*count,y_diff=1*count;

       /* DataInputStream in = new DataInputStream(sin);;
        DataOutputStream out =  new DataOutputStream(sout);

        String answer= String.valueOf(msg);

        try {
            System.out.println("Sending >> " + answer + " to the server");
            out.writeUTF(answer);
            out.flush(); // заставляем поток закончить передачу данных.
            //  String fromserver = in.readUTF();// ждем пока сервер отошлет строку текста.
            //  System.out.println(">> Server sent this :  " + fromserver);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

         step[0] = x_diff;
          step[1] = y_diff;
         return step;

    }



   @Override

    protected String doInBackground(Object... voids) {
        try {

            DataInputStream in;
            DataOutputStream out;

            System.out.println("Any of you heard of a socket with IP address " + dstAddress + " and port " + dstPort + "?");
             socket = new Socket(dstAddress, dstPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");
            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            String line = "hello";
            String from_server;

            System.out.println("Sending >> " + line + " to the server");
            out.writeUTF(line);
            out.flush(); // заставляем поток закончить передачу данных.
            from_server = in.readUTF(); // ждем пока сервер отошлет строку текста.
            System.out.println(">>Server said: " + from_server);

            who_i_am =from_server.substring(3);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}