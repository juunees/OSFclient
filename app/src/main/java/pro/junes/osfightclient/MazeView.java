package pro.junes.osfightclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by junes on 23.02.17.
 */

public class MazeView extends View {
    private GameManager gameManager;
    private Paint paint = new Paint(Color.BLACK);



    public MazeView(Context context, GameManager gameManager ){

        super(context);
        this.gameManager = gameManager;
        gameManager.setView(this);
    }

    @Override
    protected void onDraw(Canvas canvas){

        gameManager.draw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(gameManager.context.getResources(),R.drawable.logo);
        Rect rect_for_img = new Rect(1510, 30 , 1790, 220);
        canvas.drawBitmap(bitmap, null,rect_for_img ,paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){

        super.onSizeChanged(w, h, oldw, oldh);
        gameManager.setScreenSize(w,h);
    }
}


