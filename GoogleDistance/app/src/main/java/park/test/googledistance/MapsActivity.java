package park.test.googledistance;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    private int numberOfPoint = 0;
    private LatLng first;
    private LatLng second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private String otitle = "Tokyo";

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);

        LatLng tokyo = new LatLng(35.6894875, 139.6917064);
        showMap(tokyo);
    }

    private void showMap(LatLng city) {
        String mytitle = String.format("%s [%f, %f]", otitle,  city.latitude, city.longitude);

        mMap.addMarker(new MarkerOptions().position(city).title(mytitle));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(city, 12);
        mMap.animateCamera(update);
        Toast.makeText(this, mytitle, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        otitle = "";
        if (numberOfPoint == 0) {
            mMap.clear();
            first = latLng;
            numberOfPoint++;
        } else {
            numberOfPoint = 0;
            second = latLng;

            double dist = HaversineDistance.distance(first.latitude, first.longitude, second.latitude, second.longitude);
            String msg = "" + dist + "KM : ";

            Polyline line = mMap.addPolyline(new PolylineOptions().add(first, second).width(25).color(Color.BLUE).geodesic(true));
            Circle circle = mMap.addCircle(new CircleOptions().center(first).radius(dist * 1000).strokeColor(Color.RED).fillColor(0x3aff0000));
            otitle = "\n" + msg;
        }
        showMap(latLng);
    }
}
