package park.test.dynamicsineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.view.View;


public class DynamicSineView2 extends View {
    float second;
    int width;
    int height;
    int maxWidth;
    int bigRadius = 200;
    float ht = 6.0f;
    int verticalOffset = 50;

    Context context;

    public DynamicSineView2(Context context) {
        super(context);
        this.context = context;
        refreshView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        drawBigCircle(canvas);
        drawSine(canvas);
    }

    public void init() {
        width = getWidth();
        height = getHeight();
        maxWidth = Math.max(width, height);
    }

    public void drawSmallCircle(Canvas canvas, float fx, float fy, float r) {
        Paint circleIn = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleIn.setStyle(Paint.Style.FILL);
        circleIn.setColor(Color.RED);
        canvas.drawCircle(fx, fy, r, circleIn);
    }

    public void drawBigCircle(Canvas canvas) {
        Paint circleIn = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleIn.setColor(Color.LTGRAY);
        canvas.drawCircle(0 + bigRadius, 0 + bigRadius + verticalOffset, bigRadius, circleIn);

        Matrix mt = new Matrix();
        Path secondPin = new Path();
        secondPin.moveTo(0 + bigRadius, 0 + bigRadius + verticalOffset);
        secondPin.lineTo(0 + bigRadius * 2, 0 + bigRadius + verticalOffset);
        mt.setRotate(-1.0f * ht * second, 0 + bigRadius, 0 + bigRadius + verticalOffset);
        secondPin.transform(mt);

        Paint secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondPaint.setStyle(Paint.Style.STROKE);
        secondPaint.setColor(Color.GREEN);
        secondPaint.setStrokeWidth(3);
        canvas.drawPath(secondPin, secondPaint);

        float rex = (float) (bigRadius * Math.cos(Math.toRadians(-1.0f * ht * second)));
        float rey = (float) (bigRadius * Math.sin(Math.toRadians(-1.0f * ht * second)));

        drawSmallCircle(canvas, rex + bigRadius, rey + bigRadius + verticalOffset, 10);
    }

    public void drawSine(Canvas canvas) {
        Path path = new Path();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        int step = (maxWidth - bigRadius * 2) / 360;
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        path.moveTo(0 + bigRadius * 2, verticalOffset + bigRadius);
        for (int i = 0; i <= second * (int) (ht); i++) {
            path.lineTo(i * step + bigRadius * 2, (float) (bigRadius * (1 - Math.sin(Math.toRadians(i))) + verticalOffset));
        }
        canvas.drawPath(path, paint);
    }

    public void clockCalc() {
        if (second >= 59) {
            second = 0;
        } else {
            second++;
        }
        invalidate();
    }

    public void refreshView() {
        refreshViewHandler.sendEmptyMessageDelayed(0, 100);
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
