package park.test.musicmainactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class TopTrackGridActivity extends AppCompatActivity {
    GridView gv;
    ArrayList<Track> tracks = new ArrayList<Track>();
    private TrackGridAdapter gAdapter;
    private ImageButton tracksButton;
    private Spinner musickindsSpinner;
    private InputMethodManager inputMethodManager;
    private ImageListRequest ira;
    private ArrayAdapter<String> spinnerListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_track_grid);
        setTitle("Top Track Grid");
        this.inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.musickindsSpinner = (Spinner) this.findViewById(R.id.musickinds);
        this.tracksButton = (ImageButton) this.findViewById(R.id.track_button);
        ira = new ImageListRequest();
        gv = (GridView) findViewById(R.id.track_grid);
        gAdapter = new TrackGridAdapter(this, tracks, ira);
        gv.setAdapter(gAdapter);
        spinnerListAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,
                TrackTagList.readTags());
        spinnerListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        musickindsSpinner.setAdapter(spinnerListAdapter);

        this.tracksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMethodManager.hideSoftInputFromWindow(tracksButton.getWindowToken(), 0);
                TrackRequestAsync lfmTask = new TrackRequestAsync(TopTrackGridActivity.this);
                try {
                    TextView txtView = (TextView) musickindsSpinner.getSelectedView();
                    String toptrackSong = txtView.getText().toString();
                    lfmTask.execute(toptrackSong);
                } catch (Exception e) {
                    lfmTask.cancel(true);
                }
            }
        });
    }

    public void updateTracks(ArrayList<Track> toptrack) {
        this.tracks = toptrack;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gAdapter.clear();
                gAdapter.addAll(tracks);
                gAdapter.notifyDataSetChanged();
            }

        });
    }

    @Override
    protected void onDestroy() {
        gv.setAdapter(null);
        super.onDestroy();
    }

}