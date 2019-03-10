package park.test.musicmainactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToptrackListActivity extends AppCompatActivity {
    ArrayList<Track> tracks = new ArrayList<Track>();
    ListView listView;
    TrackListAdapter lAdapter;
    private ImageButton tracksButton;
    private Spinner musickindsSpinner;
    private InputMethodManager inputMethodManager;
    private ImageListRequest ira;
    private ArrayAdapter<String> spinnerListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_track_list);
        setTitle("Top Track List");
        this.inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.musickindsSpinner = (Spinner) this.findViewById(R.id.musickinds);
        this.tracksButton = (ImageButton) this.findViewById(R.id.track_button);
        ira = new ImageListRequest();
        listView = (ListView) findViewById(R.id.listView);
        lAdapter = new TrackListAdapter(this, tracks, ira);

        listView.setAdapter(lAdapter);

        spinnerListAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,
                TrackTagList.readTags());
        spinnerListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        musickindsSpinner.setAdapter(spinnerListAdapter);


        this.tracksButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inputMethodManager.hideSoftInputFromWindow(tracksButton.getWindowToken(), 0);
                TrackRequestAsync lfmTask = new TrackRequestAsync(ToptrackListActivity.this);
                try {
                    TextView txtView = (TextView) musickindsSpinner.getSelectedView();
                    String toptrackSong = txtView.getText().toString();
                    lfmTask.execute(toptrackSong);
                } catch (Exception e) {
                    lfmTask.cancel(true);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track eq = (Track) parent.getItemAtPosition(position);
                String sf = String.format("artist:%s \n track:%s", eq.getArtisturl(), eq.getTrackurl());
                alert(sf);
            }

        });

    }

    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void updateTracks(final ArrayList<Track> toptrack) {
        this.tracks = toptrack;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lAdapter.clear();
                lAdapter.addAll(toptrack);
                lAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        listView.setAdapter(null);
        super.onDestroy();
    }
}
