package park.test.googlemapclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;
import android.util.AttributeSet;
import java.text.SimpleDateFormat;

public class ClockView extends View {

    int hour;
    int minite;
    //    int second;
    int width;
    int height;
    //    int selectw;
//    int center;
    String times = "";
    String timezoneId = "Asia/Tokyo";

    public ClockView(Context context) {
        super(context);
        refreshView();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        refreshView();
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
        refreshView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.WHITE);
//        width = getWidth();
//        height = getHeight();
//        selectw = Math.min(width, height);
//        center = selectw / 2;

        Paint circleOut = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleOut.setColor(Color.DKGRAY);
        Paint circleIn = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleIn.setColor(Color.WHITE);

        canvas.drawCircle(getWidth() / 2, getWidth() / 2-10, getWidth()/2-20, circleOut);
        canvas.drawCircle(getWidth()/2, getWidth()/2-10, getWidth()/2-22, circleIn);

        Matrix mt = new Matrix();
/*
		Path secondPin = new Path();
		secondPin.moveTo(getWidth() / 2, getWidth() / 2 - 10);
		secondPin.lineTo(getWidth() / 2, 40 / 2);
		mt.setRotate(6.0f * second, getWidth() / 2, getWidth() / 2 - 10);
		secondPin.transform(mt);
		Paint secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		secondPaint.setColor(Color.GREEN);
		secondPaint.setStyle(Style.STROKE);
		secondPaint.setStrokeWidth(3);
*/
        Path minitePin = new Path();
        minitePin.moveTo(getWidth() / 2, getWidth() / 2 - 10);
        minitePin.lineTo(getWidth() / 2, 60 / 2);
        mt.setRotate(0.1f * minite, getWidth() / 2, getWidth() / 2 - 10);
        minitePin.transform(mt);
        Paint minitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minitePaint.setColor(Color.BLUE);
        minitePaint.setStyle(Style.STROKE);
        minitePaint.setStrokeWidth(7);

        Path hourPin = new Path();
        hourPin.moveTo(getWidth() / 2, getWidth() / 2 - 10);
        hourPin.lineTo(getWidth() / 2, 90 / 2);
        mt.setRotate(30.0f * ((int) hour % 12) + 0.5f * minite, getWidth() / 2, getWidth() / 2 - 10);
        hourPin.transform(mt);
        Paint hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setColor(Color.RED);
        hourPaint.setStyle(Style.STROKE);
        hourPaint.setStrokeWidth(10);

//        canvas.drawPath(secondPin, secondPaint);
        canvas.drawPath(minitePin, minitePaint);
        canvas.drawPath(hourPin, hourPaint);

        Paint degitalClockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        degitalClockPaint.setColor(Color.BLACK);
        degitalClockPaint.setStrokeWidth(10);
        degitalClockPaint.setTextSize(50);
        canvas.drawText((int) hour + " : " + ((int) minite), getWidth() / 3, getWidth()*2/3 , degitalClockPaint);
    }

    public void clockCalc() {


        TimeZone tz = TimeZone.getTimeZone(timezoneId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd a hh:mm");   //HH 24, hh 12, a AM/PM
        sdf.setTimeZone(tz);

        Calendar cal=Calendar.getInstance();
        cal.setTimeZone(tz);

        hour = cal.get(Calendar.HOUR_OF_DAY);
        minite = cal.get(Calendar.MINUTE);
        //second = cal.get(Calendar.SECOND);
        times=sdf.format(cal.getTime());
        ClockView.this.invalidate();
    }

    public void refreshView() {
        refreshViewHandler.sleep(1000 * 60);
        clockCalc();
    }

    private RefreshViewHandler refreshViewHandler = new RefreshViewHandler();
    class RefreshViewHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            refreshView();
        }
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    public String getTime() {
        return times;
    }
}
