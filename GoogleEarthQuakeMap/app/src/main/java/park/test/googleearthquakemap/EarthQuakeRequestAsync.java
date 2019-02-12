package park.test.googleearthquakemap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthQuakeRequestAsync extends AsyncTask<String, Void, ArrayList<EarthQuake>> {

    Activity activity;
    ArrayList<EarthQuake> earthquakes = new ArrayList<EarthQuake>();
    boolean isConnection = false;

    EarthRequest rfw;

    public EarthQuakeRequestAsync(Activity ac) {
        activity = ac;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        rfw = new EarthRequest();
    }

    @Override
    protected ArrayList<EarthQuake> doInBackground(String... params) {
        getEarthQuakes(params);
        return earthquakes;
    }

    public String para(String key, String value) {
        return String.format("%s=%s", key, value);
    }

    public void getEarthQuakes(String... params) {
        earthquakes.clear();

        String a = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=kyusoos";

        rfw.getEarth(a);
        isConnection = rfw.isConnection();
        earthquakes = rfw.getEarthquakes();
    }

    @Override
    protected void onPostExecute(ArrayList<EarthQuake> result) {
        super.onPostExecute(result);
        if (isConnection) {
            if (activity instanceof MapsActivity) {
                ((MapsActivity) activity).updateResult(result);
            }
        } else {

            AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);

            adBuilder.setMessage("Check Http Earthquake Connection and try again.")
                    .setTitle("No Internet Access")
                    .setPositiveButton("OK", new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).create();

            adBuilder.show();
        }
    }

    public String todate2(Date dd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dd);
    }
}
