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


public class Client extends AsyncTask<Object, Object, String> {


    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;

    Client(String addr, int port) {
        Log.i(response,"in constructor");
        dstAddress = addr;
        dstPort = port;

    }



    @Override
    protected String doInBackground(Object... voids) {


        try {

            System.out.println("Any of you heard of a socket with IP address " + dstAddress + " and port " + dstPort + "?");
            Socket socket = new Socket(dstAddress, dstPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = "hello i am 1 player";

            while (true) {

                System.out.println("Sending >> " + line +" to the server");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println(">> Server sent this :  " + line);


            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}