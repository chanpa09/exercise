package park.test.com;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int PHYSICAL = 23;
    public static final int EMOTIONAL = 28;
    public static final int INTELLECTUAL = 33;

    private BioRhythmGraph phyGraph;
    private BioRhythmGraph emoGraph;
    private BioRhythmGraph intelGraph;

    Button birthDathPicker, specifiedDatePicker, showbio;
    EditText txtbirthdate, txtthedate;
    private int mYear, mMonth, mDay;
    private int bYear, bMonth, bDay;
    private int tYear, tMonth, tDay;
    private LinearLayout graphLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birthDathPicker = findViewById(R.id.birthDatePicker);
        specifiedDatePicker = findViewById(R.id.thedate);
        txtbirthdate = findViewById(R.id.thebirthdate);
        txtthedate = findViewById(R.id.txtthedate);
        showbio = findViewById(R.id.showbio);
        graphLinearLayout = findViewById(R.id.biolayout);

        birthDathPicker.setOnClickListener(this);
        specifiedDatePicker.setOnClickListener(this);
        showbio.setOnClickListener(this);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        getDate();
    }

    private void getDate() {
        String birthDayStr = txtbirthdate.getText().toString();
        String basicDayStr = txtthedate.getText().toString();

        if (isValidDate(birthDayStr)) {
            String[] bt = birthDayStr.split("-");

            bYear = Integer.parseInt(bt[0]);
            bMonth = Integer.parseInt(bt[1]);
            bDay = Integer.parseInt(bt[2]);
        }

        if (isValidDate(basicDayStr)) {
            String[] bt = basicDayStr.split("-");

            bYear = Integer.parseInt(bt[0]);
            bMonth = Integer.parseInt(bt[1]);
            bDay = Integer.parseInt(bt[2]);
        }
    }

    public boolean isValidDate(String input) {
        SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateFormatParser.parse(input);
            return true;
        } catch (Exception Ex) {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == birthDathPicker) {
            int temYear = mYear;
            int temMonth = mMonth;
            int temDay = mDay;

            if (bYear > 0) {
                temYear = bYear;
                temMonth = bMonth;
                temDay = bDay;
            }

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    bYear = year;
                    bMonth = monthOfYear + 1;
                    bDay = dayOfMonth;
                    txtbirthdate.setText(bYear + "-" + bMonth + "-" + bDay);
                }
            }, temYear, temMonth, temDay);
            datePickerDialog.show();
        } else if (v == specifiedDatePicker) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    tYear = year;
                    tMonth = monthOfYear + 1;
                    tDay = dayOfMonth;
                    txtthedate.setText(tYear + "-" + tMonth + "-" + tDay);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v == showbio && bYear != 0 && tYear != 0) {
            Calendar birth = Calendar.getInstance();
            birth.set(bYear, bMonth - 1, bDay);
            Calendar theDay = Calendar.getInstance();
            theDay.set(tYear, tMonth - 1, tDay);

            phyGraph = new BioRhythmGraph(getApplicationContext(), PHYSICAL, birth, theDay);
            emoGraph = new BioRhythmGraph(getApplicationContext(), EMOTIONAL, birth, theDay);
            intelGraph = new BioRhythmGraph(getApplicationContext(), INTELLECTUAL, birth, theDay);

            int properHeight = graphLinearLayout.getHeight() / 3;

            graphLinearLayout.removeAllViews();
            phyGraph.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, properHeight));
            emoGraph.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, properHeight));
            intelGraph.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, properHeight));

            graphLinearLayout.addView(phyGraph);
            graphLinearLayout.addView(emoGraph);
            graphLinearLayout.addView(intelGraph);

            phyGraph.invalidate();
            emoGraph.invalidate();
            intelGraph.invalidate();
        }
    }
}
