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



    Client(String addr, int port)  {
        dstAddress = addr;
        dstPort = port;
    }

    public void createConnection() {

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

            String line = "player1";

                System.out.println("Sending >> " + line + " to the server");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println(">> Server sent this :  " + line);




        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}