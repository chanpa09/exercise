package park.test.clockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Message;
import android.view.View;
import android.os.Handler;

import java.util.Calendar;
import java.util.TimeZone;

public class ClockView extends View {
    int hour;
    int minute;
    int second;
    int width;
    int height;
    int selectw;
    int center;

    public ClockView(Context context) {
        super(context);
        refreshView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.WHITE);
        width = getWidth();
        height = getHeight();
        selectw = Math.min(width, height);
        center = selectw / 2;

        Paint circleOut = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleOut.setColor(Color.DKGRAY);
        Paint circleIn = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleIn.setColor(Color.WHITE);

        canvas.drawCircle(center, center - 10, center - 20, circleOut);
        canvas.drawCircle(center, center - 10, center - 50, circleIn);

        Matrix mt = new Matrix();

        Path secondPin = new Path();
        secondPin.moveTo(center, center - 10);
        secondPin.lineTo(center, 60);
        mt.setRotate(6.0f * second, center, center - 10);
        secondPin.transform(mt);
        Paint secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondPaint.setColor(Color.GREEN);
        secondPaint.setStyle(Paint.Style.STROKE);
        secondPaint.setStrokeWidth(3);

        Path minitePin = new Path();
        minitePin.moveTo(center, center - 10);
        minitePin.lineTo(center, 90);
        mt.setRotate(6.0f * minute + 0.1f * second, center, center - 10);
        minitePin.transform(mt);
        Paint minitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minitePaint.setColor(Color.BLUE);
        minitePaint.setStyle(Paint.Style.STROKE);
        minitePaint.setStrokeWidth(8);

        Path hourPin = new Path();
        hourPin.moveTo(center, center - 10);
        hourPin.lineTo(center, 120);
        mt.setRotate(30.0f * (hour % 12) + 0.5f * minute, center, center - 10);
        hourPin.transform(mt);
        Paint hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setColor(Color.RED);
        hourPaint.setStyle(Paint.Style.STROKE);
        hourPaint.setStrokeWidth(15);

        canvas.drawPath(secondPin, secondPaint);
        canvas.drawPath(minitePin, minitePaint);
        canvas.drawPath(hourPin, hourPaint);

        Paint degitalClockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        degitalClockPaint.setColor(Color.BLACK);
        degitalClockPaint.setStrokeWidth(10);
        degitalClockPaint.setTextSize(selectw / 10);
        canvas.drawText(hour + " : " + minute + " : " + second, selectw / 3, selectw * 2 / 3, degitalClockPaint);
    }

    public void clockCalc() {
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(tz);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
        invalidate();
    }

    public void refreshView() {
        refreshViewHandler.sendEmptyMessageDelayed(0, 1000);
        clockCalc();
    }

    private RefreshViewHandler refreshViewHandler = new RefreshViewHandler();

    class RefreshViewHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            refreshView();
        }
    }
}
