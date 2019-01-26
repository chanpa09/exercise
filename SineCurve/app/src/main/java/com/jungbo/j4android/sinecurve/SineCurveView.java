package com.jungbo.j4android.sinecurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class SineCurveView extends View {

    private int verticalCenter;
    private int verticalOffset;
    private int period;
    private float dayUnit;
    private int height;
    private int horizontalCenter;

    public SineCurveView(Context context){
        super(context);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAxis(canvas);
        makeGraph(canvas);
    }

    private void drawAxis(Canvas canvas) {
        setBackgroundColor(Color.BLACK);
        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        verticalOffset = 30;
        height = getHeight() - verticalOffset;
        height /= 3;
        verticalCenter = height / 2;
        period = getWidth();
        dayUnit = period / 12;
        horizontalCenter = period / 2;

        path.moveTo(0, verticalOffset);
        path.lineTo(period, verticalOffset);
        canvas.drawPath(path, paint);

        path.moveTo(0, verticalCenter + verticalOffset);
        path.lineTo(period, verticalCenter + verticalOffset);
        canvas.drawPath(path, paint);

        path.moveTo(0, height + verticalOffset);
        path.lineTo(period, height + verticalOffset);
        canvas.drawPath(path, paint);

        for(int i =0;i<=period;i+=dayUnit){
            path.moveTo(i,verticalOffset);
            path.lineTo(i,height+verticalOffset);
        }

        canvas.drawPath(path,paint);
    }
    private double getBioRhythmValue(float n){
        return verticalCenter*Math.sin(Math.toRadians(n));
    }

    public  void makeGraph(Canvas canvas){
        Path path = new Path();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        path.moveTo(0,-(float)getBioRhythmValue(0)+verticalCenter+verticalOffset);
        for(int i =0;i<=360;i++){
            path.lineTo(i*(dayUnit/30.0f),-(float)getBioRhythmValue(i)+verticalCenter+verticalOffset);
        }
        canvas.drawPath(path,paint);
    }


}
