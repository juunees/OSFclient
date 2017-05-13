package pro.junes.osfightclient;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by junes on 23.02.17.
 */

public class Exit extends Dot {

    public Exit(Point point, int size) {
        super(size, point, getPaint(),R.drawable.computer);

    }

    private static Paint getPaint(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.argb(250,94, 87, 87));
        return paint;
    }

}

