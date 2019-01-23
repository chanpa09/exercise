package com.jungbo.j4android.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtbirthdate;
    Button showbio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showComponet();
    }

    public void showComponet() {
        txtbirthdate = (EditText) findViewById(R.id.txtBirthDate);
        showbio = (Button) findViewById(R.id.button_today);

        showbio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == showbio) {
            String[] _dateStrList = {"日", "月", "火", "水", "木", "金", "土"};
            int _dt= Calendar.DAY_OF_WEEK;

            String st = String.format("今日は%s曜日です。", _dateStrList[_dt-1]);
            txtbirthdate.setText(st);

            Toast.makeText(getBaseContext(), st, Toast.LENGTH_SHORT).show();
        }
    }
}
