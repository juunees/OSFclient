package pro.junes.osfightclient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by junes on 23.02.17.
 */

public interface Drawable {

    void draw(Canvas canvas, Rect rect, Context context);

}
