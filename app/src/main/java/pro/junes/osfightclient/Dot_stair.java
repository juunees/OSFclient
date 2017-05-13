package pro.junes.osfightclient;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by junes on 23.02.17.
 */

public class Dot_stair extends Dot {

    public Dot_stair(Point point, int size, int id_res) {
        super(size, point, getPaint(), id_res);
    }

    private static Paint getPaint(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.argb(150,75, 70, 70));
        return paint;
    }
}