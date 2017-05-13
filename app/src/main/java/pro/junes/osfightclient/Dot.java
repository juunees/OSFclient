package pro.junes.osfightclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by junes on 23.02.17.
 */

public class Dot implements Drawable {


    protected Point point;
    protected Paint paint;
    private int size;
    private int id_resource;

    public Dot(int size, Point point, Paint paint, int id_res){

        this.point = point;
        this.paint = paint;
        this.size = size;
        this.id_resource = id_res;

    }

    public Point getPoint(){
        return point;
    }



    @Override
    public void draw(Canvas canvas, Rect rect, Context context) {

        float cellSize = (float) (rect.right - rect.left)/size;

        canvas.drawRect(
                rect.left + point.x*cellSize,
                rect.top + point.y*cellSize,
                rect.left + point.x*cellSize + cellSize,
                rect.top + point.y*cellSize + cellSize,
                paint);


        if(id_resource == R.drawable.computer ){

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),id_resource);
            Rect rect_for_img = new Rect(rect.left + point.x*(int)cellSize - 20,
                    rect.top + point.y*(int)cellSize - 20,
                    rect.left + point.x*(int)cellSize + (int)cellSize + 20,
                    rect.top + point.y*(int)cellSize + (int)cellSize + 20);
            canvas.drawBitmap(bitmap, null,rect_for_img ,paint);
        }

        else {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id_resource);
            // Rect r = new Rect(rect.left+point.x*(int)cellSize,rect.top+point.y*(int)cellSize,rect.right,rect.bottom);

            Rect rect_for_img = new Rect(rect.left + point.x * (int) cellSize - 10,
                    rect.top + point.y * (int) cellSize - 10,
                    rect.left + point.x * (int) cellSize + (int) cellSize + 10,
                    rect.top + point.y * (int) cellSize + (int) cellSize + 10);

            // Matrix maxtix = new Matrix();
            canvas.drawBitmap(bitmap, null, rect_for_img, paint);
        }
    }

}
