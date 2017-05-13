package pro.junes.osfightclient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by junes on 23.02.17.
 */

public class Maze implements Drawable {

    private Paint wallPaint;
    private final boolean[][] array;
    private final int size_x;
    private final int size_y;
    private final Point end = new Point(17,9);
    private int bestScore = 0;
    private  Point start = new Point(1,1) ;
    private Point start_2 = new Point(31,17);
    private Point [] dots_trampoline ;
    private Point [] dots_stair ;


    public Maze(int size_x,int size_y){

        wallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        wallPaint.setARGB(150,189, 62, 62);

        this.size_x = size_x;
        this.size_y = size_y;
        array = new boolean[size_y][size_x];

        genetareMaze();
        this.dots_trampoline = set_spesial_dots();
        this.dots_stair = set_spesial_dots();

    }

    private void genetareMaze(){

        for(int i = 0 ; i < size_y ; ++i ){
            for(int j = 0; j < size_x ; ++j){
                array[i][j] = i %2 != 0 && j%2 !=0   // стенки лабиринта на нечетных позициях
                        && i < size_y-1 && j < size_x-1;
            }
        }

        Random random = new Random();
        Stack<Point> stack = new Stack<>();   // путь
        stack.push(end);

        while (stack.size()>0){

            Point current = stack.peek();
            List<Point> unusedNeighbors = new LinkedList<>();
            //left
            if(current.x > 2){
                if (!isUsedCell(current.x - 2 ,current.y)){
                    unusedNeighbors.add(new Point(current.x-2,current.y));
                }
            }
            //top
            if(current.y > 2){
                if (!isUsedCell(current.x ,current.y - 2)){
                    unusedNeighbors.add(new Point(current.x,current.y-2));
                }
            }
            //right
            if(current.x <  size_x-2){
                if (!isUsedCell(current.x + 2 ,current.y )){
                    unusedNeighbors.add(new Point(current.x+2,current.y));
                }
            }
            //bottom
            if(current.y <  size_y-2){
                if (!isUsedCell(current.x ,current.y+2)){
                    unusedNeighbors.add(new Point(current.x,current.y+2));
                }
            }

            // если список соседей не пуст , случайно выбираем куда пойти
            if(unusedNeighbors.size()> 0){

                int rnd = random.nextInt(unusedNeighbors.size());

                Point direction = unusedNeighbors.get(rnd);

                int diffX = (direction.x - current.x)/2;
                int diffY = (direction.y - current.y)/2;
                array[current.y + diffY][current.x + diffX] = true;    // убираем стену

                stack.push(direction);


            } else{
                if(bestScore < stack.size()) {
                    bestScore = stack.size();
                    // start = current;
                }

                stack.pop();   // если из клетки уже идти некуда, сходили из нее во всех направлениях - ее выкидываем
            }

        }

    }

    public int getSize_x() {
        return size_x;
    }

    public int getSize_y() {
        return size_y;
    }

    public Point getStart() {
        return start;
    }

    public Point getStart2() {


        return start_2;
    }

    public Point getEnd() {
        return end;
    }

    public Point[] get_trampoline_dots(){return dots_trampoline;}

    public Point[] get_stair_dots(){return dots_stair;}

    private Point[] set_spesial_dots(){

        Point[] points = new Point[3];

        for(int i = 0 ; i < 3; i++ ) {
            points[i] = set_spesial_dot();
        }
        return points;
    }


    public boolean canPlayerGoTo(int x, int y){
        return array[y][x];
    }

    public boolean isCrossroad(int x, int y){
        return isUsedCell(x,y);
    }

    private boolean isUsedCell(int x , int y){   // все ли стенки закрыты
        if(x < 0 || y < 0 || x >= size_x-1 || y >= size_y-1){
            return true;
        }
        return array[y-1][x] //left
                || array[y][x-1] //top
                || array[y+1][x] // right
                || array[y][x+1]; //bottom

    }

    private Point set_spesial_dot(){


        Point point = new Point(1,1);
        Random random = new Random();
        int rnd_x = random.nextInt(size_x-5) + 2;
        int rnd_y = random.nextInt(size_y-5) + 2;

        if(rnd_x==31 && rnd_y ==17){                                                  // чтобы не в центре

            if(array[rnd_y - 1][rnd_x]==true){ return new Point(rnd_x,rnd_y - 1 ); }
            if(array[rnd_y + 1][rnd_x]==true){ return  new Point(rnd_x,rnd_y + 1 ); }
            if(array[rnd_y ][rnd_x - 1]==true){ return new Point(rnd_x - 1,rnd_y ); }
            if(array[rnd_y ][rnd_x + 1]==true){ return  new Point(rnd_x + 1,rnd_y ); }

        }

        if(array[rnd_y][rnd_x]==true){return new Point(rnd_x,rnd_y);}

        else{
            if(array[rnd_y - 1][rnd_x]==true){ return new Point(rnd_x,rnd_y - 1 ); }
            if(array[rnd_y + 1][rnd_x]==true){ return  new Point(rnd_x,rnd_y + 1 ); }
            if(array[rnd_y ][rnd_x - 1]==true){ return new Point(rnd_x - 1,rnd_y ); }
            if(array[rnd_y ][rnd_x + 1]==true){ return  new Point(rnd_x + 1,rnd_y ); }
        }
        return point;
    }

    public void climb(Point p){


        boolean b = false;

        if(p.x < size_x - 2 && !b)
            if (!array[p.y][p.x + 1]) {array[p.y][p.x + 1]=true; b = true;}

        if(p.y < size_y - 2  && !b)
            if(!array[p.y + 1][p.x]) {array[p.y + 1][p.x]=true; b = true; }

        if(p.x > 2 && !b )
            if(!array[p.y][p.x - 1] ) {array[p.y][p.x - 1]=true; b = true; }

        if(p.y > 2 && !b )
            if(!array[p.y - 1][p.x] ) {array[p.y - 1][p.x]=true; b = true; }


    }

    @Override
    public void draw(Canvas canvas, Rect rect, Context context) {


        float cellSize = (float) (rect.right - rect.left)/size_x;
        for(int i = 0 ; i < size_y; ++i){
            for(int j = 0 ; j < size_x; ++j){
                if(!array[i][j]){   //если в ячейке false рисуем стену
                    float left = j*cellSize + rect.left;    // по x
                    float top = i*cellSize + rect.top;    // по y
                    canvas.drawRect(
                            left,
                            top,
                            left + cellSize,
                            top + cellSize,
                            wallPaint
                    );
                }
            }
        }

    }

}
