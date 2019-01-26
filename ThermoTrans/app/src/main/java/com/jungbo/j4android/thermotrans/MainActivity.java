package com.jungbo.j4android.thermotrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editCent;
    EditText editFha;
    RadioButton rtoFha;
    RadioButton rtoCent;
    Button convert;
    Button cancell;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        AdView mAdView = (AdView) findViewById(R.id.adView);AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        MobileAds.initialize(this, "ca-app-pub-4830018604875325~1132772017");

        editCent = (EditText) this.findViewById(R.id.editCent);
        editFha = (EditText) this.findViewById(R.id.editFha);
        rtoFha = (RadioButton) this.findViewById(R.id.rtoFha);
        rtoFha.setChecked(true);
        rtoCent = (RadioButton) this.findViewById(R.id.rtoCent);
        convert = (Button) this.findViewById(R.id.convert);
        cancell = (Button) this.findViewById(R.id.cancell);

        convert.setOnClickListener(this);
        cancell.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == convert && rtoFha.isChecked()) {
            double val = Double.parseDouble(editCent.getText().toString());
            convertCentToFha(val);
        } else if (v == convert && rtoCent.isChecked()) {
            double val = Double.parseDouble(editFha.getText().toString());
            convertFhaToCent(val);
        } else if (v == cancell) {
            String _ini = "0.0";
            editCent.setText(_ini);
            editFha.setText(_ini);
        }
    }

    protected String formats(double degree) {
        return String.format("%1$.2f", degree);
    }

    public double celsiusToFahrenheit(double c) {
        return 9.0 / 5.0 * c + 32.0;
    }

    public double fahrenheitToCelsius(double f) {
        return 5.0 / 9.0 * (f - 32);
    }

    protected void convertCentToFha(double val) {
        editFha.setText(formats(celsiusToFahrenheit(val)));
    }

    protected void convertFhaToCent(double val) {
        editCent.setText(formats(fahrenheitToCelsius(val)));
    }

}
