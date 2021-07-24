package com.example.deltatask_2b;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.Random;

public class myCanvas extends View{
    private Bitmap ball;
    private Bitmap slider;
    private Bitmap restart;
    private Bitmap gameOver;
    private Bitmap bg_cute;
    int x,y,vx,vy;
    int a=0;
    int left;
    int xPosition,yPosition;
    int cH,cW;
    int slider_x;
    Paint black;
    Paint red;
    public myCanvas(Context context) {
        super(context);


        ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        slider = BitmapFactory.decodeResource(getResources(),R.drawable.black_slider);
        restart = BitmapFactory.decodeResource(getResources(), R.drawable.restart);
        gameOver = BitmapFactory.decodeResource(getResources(),R.drawable.game_over);
        bg_cute = BitmapFactory.decodeResource(getResources(),R.drawable.bg_cute);
        x = 0;
        y = 0;
        Random num = new Random();
        vx = 10 + num.nextInt(5);
        vy = 10 + num.nextInt(5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x = vx + x;
        y = vy + y;
        black = new Paint(Paint.ANTI_ALIAS_FLAG);
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);
        black.setTextSize(100);
        red = new Paint(Paint.ANTI_ALIAS_FLAG);
        red.setColor(Color.argb(250, 252, 0,0));
        red.setStyle(Paint.Style.FILL);
        red.setTextSize(130);


        if (x<= 0){
            vx = 20;
        }
        if (y<= 120){
            vy = 20;
        }
        if (x>=cW-ball.getWidth()){
            vx = -20;
        }
        if ((y>= canvas.getHeight()-ball.getHeight()-slider.getHeight()) &&
                (x>= left- ball.getWidth()/2) && (x<= left +slider.getWidth()) && (y<= canvas.getHeight()-slider.getHeight())){
            vy = -20;
            a = a+2;
        }
        if (y>= canvas.getHeight()){
            vx = 0;
            vy = 0;


            canvas.drawBitmap(bg_cute,0,0,null);
            canvas.drawBitmap(restart,190, 1000, null);
            canvas.drawText("GAME OVER",200, 800,red);

        }

        cH = canvas.getHeight();
        cW = canvas.getWidth();
        canvas.drawBitmap(ball,x,y,null);
        invalidate();
        if(slider_x+ xPosition>= cW-slider.getWidth()){
            left = slider_x + xPosition - slider.getWidth();

        }
        else{
            left = slider_x + xPosition;

        }
        canvas.drawBitmap(slider,left,cH-slider.getHeight() , null);
        canvas.drawText("Score: "+ a, 0, 100, black);

    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xPosition = (int) event.getX();
        yPosition = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (xPosition >= left && xPosition < left + slider.getWidth() && yPosition >= cH- slider.getHeight() && y <= cH) {
                    xPosition += 1;
                }
                if (xPosition >= 190 && xPosition <= 190+restart.getWidth() && yPosition >=1000 && yPosition<=1000+restart.getHeight()){
                    Intent resultIntent = new Intent(getContext(),MainActivity.class);
                    resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(resultIntent);

                }
                return true;


        }
        return false;
    }

}