package pro.junes.osfightclient;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by junes on 23.02.17.
 */

public class Player extends Dot {

    public Player(Point start, int  size, int id_img){

        super(size,start,getPaint(),id_img);

    }

    private static Paint getPaint(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.argb(250,75, 70, 70));
        return paint;
    }


    public void goTo(int x, int y){
        point.x = x;
        point.y = y;
    }


    public int getX(){
        return point.x;
    }

    public int getY(){
        return point.y;
    }
}
