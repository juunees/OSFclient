package pro.junes.osfightclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static android.R.attr.left;
import static android.R.attr.right;
import static pro.junes.osfightclient.R.id.bottom;
import static pro.junes.osfightclient.R.id.top;

/**
 * Created by junes on 29.03.17.
 */

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btnActPlay = (Button) findViewById(R.id.buttonPlay);
        btnActPlay.setOnClickListener(this);

        Button btnActStatistic = (Button) findViewById(R.id.buttonStatistic);
        btnActStatistic.setOnClickListener(this);



       /* Client client = new Client();
        String[] arguments = new String[] {"123"};
        client.main(arguments);*/


    }

   /* public static void main(String[] ar) {

        Client client = new Client();
        client.main(ar);

    }*/


    @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonPlay:
                    Intent intent1 = new Intent(this, PlayerActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.buttonStatistic:
                    Intent intent2 = new Intent(this, StatisticActivity.class);
                    startActivity(intent2);
                    break;
            }
        }


}
