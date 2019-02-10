package park.test.googlemapclock;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        showComponent();
    }

    public void showComponent() {
        mMap.setInfoWindowAdapter(new MapInfoWindowAdapter());

        LatLng tokyo = new LatLng(35.6894875, 139.6917064);
        LatLng seoul = new LatLng(37.5670, 126.9807);
        LatLng austria = new LatLng(47.01, 10.2);
        LatLng newyork = new LatLng(40.714086, -74.228697);
        LatLng mexico = new LatLng(19.42847, -99.12766);
        LatLng china = new LatLng(39.9075, 116.39723);
        LatLng russia = new LatLng(55.75222, 37.61556);

        addMyMarker(tokyo, "Asia/Tokyo");
        addMyMarker(seoul, "Asia/Seoul");
        addMyMarker(austria, "Europe/Vienna");
        addMyMarker(newyork, "America/New_York");
        addMyMarker(mexico, "America/Mexico_City");
        addMyMarker(china, "Asia/Shanghai");
        addMyMarker(russia, "Europe/Moscow");

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(tokyo, 1);
        mMap.animateCamera(update);
    }

    public void addMyMarker(LatLng nation, String timezoneId) {
        String nationtitle = String.format("%f, %f", nation.latitude, nation.longitude);

        mMap.addMarker(new MarkerOptions().position(nation).title(nationtitle).snippet(timezoneId));
    }

    private class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View mInfoWindow;

        public MapInfoWindowAdapter() {
            mInfoWindow = getLayoutInflater().inflate(R.layout.map_infor_window, null);
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            String timezoneId = marker.getSnippet();

            ClockView cv = (ClockView) mInfoWindow.findViewById(R.id.flagimage);
            cv.setTimezoneId(timezoneId);
            String title = String.format("%s [%s]", cv.getTime(), marker.getTitle());

            TextView tvTitle = (TextView) mInfoWindow.findViewById(R.id.tvTitle);
            tvTitle.setText(title);

            TextView tvSnippet = (TextView) mInfoWindow.findViewById(R.id.tvSnippet);
            tvSnippet.setText(timezoneId);

            return mInfoWindow;
        }
    }
}
