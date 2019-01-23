package com.jungbo.j4android.circle000;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class EarthquakeView extends View {

    public EarthquakeView(Context context) {
        super(context);
    }

    public EarthquakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EarthquakeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EarthquakeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private double magnitude = 0.0;

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float select = Math.min(width, height);
        float ratio = select / 500;

        setBackgroundColor(Color.WHITE);
        Paint circleOut = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (magnitude >= 8.0) {
            showCircle(canvas, width / 2, height / 2, magnitude * 11 * ratio, circleOut, Color.RED);
        } else if (magnitude >= 7.0) {
            showCircle(canvas, width / 2, height / 2, magnitude * 8 * ratio, circleOut, Color.BLUE);
        } else if (magnitude >= 6.0) {
            showCircle(canvas, width / 2, height / 2, magnitude * 5 * ratio, circleOut, Color.BLACK);
        } else {
            showCircle(canvas, width / 2, height / 2, magnitude * 2 * ratio, circleOut, Color.GREEN);
        }

    }

    public void showCircle(Canvas canvas, float width, float height, double magnitude, Paint paint, int color) {
        paint.setColor(color);
        canvas.drawCircle(width, height, (float) magnitude, paint);
    }

}