package park.test.com;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.Calendar;

public class BioRhythmGraph extends View {

    public static final int PHYSICAL = 23;
    public static final int EMOTIONAL = 28;
    public static final int INTELLECTUAL = 33;

    private int index = 23;
    private Calendar birth = null;
    private Calendar theDay = null;

    private int daysFromBirth;
    private int verticalCenter;
    private int verticalOffset;
    private int period = 23;
    private int dayUnit;
    private int height;

    public BioRhythmGraph(Context context, int index) {
        super(context);
        this.index = index;
    }

    public BioRhythmGraph(Context context, int index, Calendar birth, Calendar theDay) {
        this(context, index);
        this.birth = birth;
        this.theDay = theDay;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (birth != null && theDay != null) {
            canvas.drawColor(Color.BLACK);
            drawAxis(canvas);
            makeGraph(canvas);
            drawText(canvas);
        }
    }

    private void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getLineColor());
        paint.setTextSize(60);
        canvas.drawText(generateTextInformation(getBioRhythmValue(daysFromBirth)), 0, 60, paint);
    }

    private void drawAxis(Canvas canvas) {
        Paint paint = new Paint();
        Path path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);

        verticalOffset = 15;
        int margin = 50;
        height = getHeight() - margin;
        verticalCenter = height / 2;
        period = getWidth();
        dayUnit = period / 29;

        path.moveTo(0, verticalOffset);
        path.lineTo(period, verticalOffset);
        canvas.drawPath(path, paint);

        path.moveTo(0, verticalCenter + verticalOffset);
        path.lineTo(period, verticalCenter + verticalOffset);
        canvas.drawPath(path, paint);

        path.moveTo(0, height + verticalOffset);
        path.lineTo(period, height + verticalOffset);
        canvas.drawPath(path, paint);

        for (int i = 0, counter = 0; i <= period; i += dayUnit, counter++) {
            path.moveTo(i, verticalOffset);
            path.lineTo(i, height + verticalOffset);
        }

        canvas.drawPath(path, paint);

        path = new Path();
        paint.setColor(Color.YELLOW);
        path.moveTo(dayUnit * 15, verticalOffset);
        path.lineTo(dayUnit * 15, height + verticalOffset);
        canvas.drawPath(path, paint);
    }

    public void makeGraph(Canvas canvas) {
        Path path = new Path();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getLineColor());
        daysFromBirth = daysFromBirth();
        int startDay = daysFromBirth - 15;
        path.moveTo(0, -(float) getBioRhythmValue(startDay) + verticalCenter + verticalOffset);
        for (int i = dayUnit, j = startDay + 1; i <= period; i += dayUnit, j++) {
            path.lineTo(i, -(float) getBioRhythmValue(j) + verticalCenter + verticalOffset);
        }
        canvas.drawPath(path, paint);
    }

    private String generateTextInformation(double value) {
        String result = "";
        switch (index) {
            case PHYSICAL:
                result = "身体指数";
                break;
            case EMOTIONAL:
                result = "感情指数";
                break;
            case INTELLECTUAL:
                result = "知性指数";
                break;
        }

        return String.format("%s %.2f", result, (value * 100.0 / verticalCenter));
    }

    private double getBioRhythmValue(int days) {
        return verticalCenter * Math.sin((days % index) * 2 * Math.PI / index);
    }

    private int daysFromBirth() {
        long dateBirth = birth.getTimeInMillis();
        long dateToDay = theDay.getTimeInMillis();
        long days = dateToDay - dateBirth;
        return (int) (days / 1000 / 24 / 60 / 60);
    }

    private int getLineColor() {
        switch (index) {
            case PHYSICAL:
                return Color.RED;
            case EMOTIONAL:
                return Color.GREEN;
            case INTELLECTUAL:
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }


}
