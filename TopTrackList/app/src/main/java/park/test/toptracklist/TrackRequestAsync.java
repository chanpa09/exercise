package park.test.toptracklist;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;

public class TrackRequestAsync extends AsyncTask<String, Void, ArrayList<Track>> {

    Activity activity;
    ProgressBar progressBar;
    boolean isConnection = false;
    ArrayList<Track> tracks = new ArrayList<>();

    public TrackRequestAsync(Activity ac) {
        activity = ac;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBarStart();
    }

    @Override
    protected ArrayList<Track> doInBackground(String... params) {
        InputStream inputStream;
        String jsonString = "";
        String newUrls = MusicUtil.TOPTRACK + params[0];

        

        return tracks;
    }

    @Override
    protected void onPostExecute(ArrayList<Track> result) {

    }

    private synchronized void parseTrackToJson(String json) throws JSONException {

    }

    private void progressBarStart() {
        progressBar = activity.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void progressBarClose() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
