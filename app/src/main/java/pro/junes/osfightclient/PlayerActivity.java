package pro.junes.osfightclient;

import android.graphics.drawable.*;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static android.R.attr.left;
import static android.R.attr.right;
import static pro.junes.osfightclient.R.id.bottom;
import static pro.junes.osfightclient.R.id.top;

/**
 * Created by junes on 23.02.17.
 */

public class PlayerActivity extends AppCompatActivity {

    private MazeView view;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String msg="";
        Log.i(msg,">>Client connection .....");
        Client client = new Client("10.0.2.2",8080);
         client.execute();

        // создать клиента

        //client.run();


        GameManager gameManager = new GameManager(getApplicationContext());

        view = new MazeView(this,gameManager);

        android.graphics.drawable.Drawable background = getResources().getDrawable(R.drawable.background);
        background.setBounds(left, top, right, bottom);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(background);
        }

        setContentView(view);
        gestureDetector = new GestureDetector(this,gameManager);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }
}
