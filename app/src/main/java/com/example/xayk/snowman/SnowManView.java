package com.example.xayk.snowman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by XayK on 23.01.2018.
 */

public class SnowManView extends View {

    float x=50,y=50;
    float r_top=100,r_middle=150,r_bottom=200;
    boolean top=true,middle=true,bottom = true;
    double [][] snowflakes = new double[100][2];


    public SnowManView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        for(int i=0;i<100;i++)
        {
            snowflakes[i][0]=Math.random();
            snowflakes[i][1]=Math.random();

        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        x=canvas.getWidth()/2;
        y=canvas.getHeight()/4;

        Paint p =  new Paint();
        p.setColor(Color.WHITE);

        canvas.drawColor(Color.BLACK);

        if(top)
            canvas.drawCircle(x,y,r_top,p);
        if(middle)
            canvas.drawCircle(x,y+r_top+r_middle,r_middle,p);
        if(bottom)
            canvas.drawCircle(x,y+r_top+r_middle*2+r_bottom,r_bottom,p);

        for(int i=0;i<100;i++)
        {
            canvas.drawCircle((float)snowflakes[i][0]*canvas.getWidth(),(float) snowflakes[i][1]*canvas.getHeight(),5,p);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float X=event.getX();
        float Y=event.getY();

        if(Math.hypot(x-X,y-Y)<r_top)
            top=!top;

        else if(Math.hypot(x-X,y+r_top+r_middle-Y)<r_middle)
            middle=!middle;

        else if(Math.hypot(x-X,y+r_top+r_middle*2+r_bottom-Y)<r_bottom)
            bottom=!bottom;

        for(int i=0;i<100;i++)
        {
            snowflakes[i][1]+=0.01;
            if(snowflakes[i][1]>1)snowflakes[i][1]-=1;
        }
        invalidate();

        return super.onTouchEvent(event);
    }
}
