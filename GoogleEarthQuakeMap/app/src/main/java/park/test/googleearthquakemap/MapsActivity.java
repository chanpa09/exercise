package park.test.googleearthquakemap;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EarthQuakeRequestAsync async;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng tokyo = new LatLng(37.94, 138.755);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(tokyo, 6);
        mMap.animateCamera(update);

        async = new EarthQuakeRequestAsync(this);
        async.execute();
        Toast.makeText(this, "Ready...", Toast.LENGTH_LONG).show();
    }

    public void updateResult(final ArrayList<EarthQuake> result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (EarthQuake earth : result) {
                    double magnitude = earth.getMagnitude();
                    LatLng loc = new LatLng(earth.getLatitude(), earth.getLongitude());

                    if (magnitude >= 5.0) {
                        earthCircle(mMap, loc, magnitude * 2000, Color.RED, 0x9aff0000);
                    } else if (magnitude >= 4.0) {
                        earthCircle(mMap, loc, magnitude * 1500, Color.RED, 0x7aff0000);
                    } else if (magnitude >= 3.0) {
                        earthCircle(mMap, loc, magnitude * 1000, Color.BLUE, 0x5a0000f);
                    } else {
                        earthCircle(mMap, loc, magnitude * 500, Color.BLUE, 0x3a0000ff);
                    }
                }
            }
        });
    }

    public void earthCircle(GoogleMap googleMap, LatLng lat, double magni, int color, int fcolor) {
        double mag = 1.0;
        Circle circle = googleMap.addCircle(new CircleOptions().center(lat).
                radius(magni).strokeColor(color).fillColor(fcolor));
    }

    public String todate2(Date dd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dd);
    }
}
