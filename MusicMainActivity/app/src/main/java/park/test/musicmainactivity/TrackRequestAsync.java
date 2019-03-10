package park.test.musicmainactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TrackRequestAsync extends AsyncTask<String, Void, ArrayList<Track>> {
    Activity activity;
    boolean isConnection = false;

    ArrayList<Track> tracks = new ArrayList<Track>();

    public TrackRequestAsync(Activity ac) {
        activity = ac;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Track> doInBackground(String... params) {
        InputStream inputStream;
        String jsonString = "";
        String newUrls = MusicUtil.TOPTRACK + params[0];
        try {
            Log.i("doInBackground", "-----------------------------------------" + newUrls);
            URL url = new URL(newUrls);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            jsonString = sb.toString();
            inputStream.close();
            parseTrackToJson(jsonString);
            isConnection = true;
        } catch (Exception e) {
            Log.e("HTTP Request", e.getMessage());
            isConnection = false;
        }

        return tracks;

    }


    @Override
    protected void onPostExecute(ArrayList<Track> result) {
        super.onPostExecute(result);
        if (isConnection) {
            if (activity instanceof ToptrackListActivity) {
                ((ToptrackListActivity) activity).updateTracks(result);
            } else if (activity instanceof TopTrackGridActivity) {
                ((TopTrackGridActivity) activity).updateTracks(result);
            }
        } else {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(activity);
            adBuilder.setMessage("Check Http Track Connection and try again.")
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

    private synchronized void parseTrackToJson(String json) throws JSONException {
        tracks.clear();
        JSONObject jObject = new JSONObject(json);
        JSONObject topTracksObj = jObject.getJSONObject("tracks");//tracks
        try {
            JSONArray tracks = topTracksObj.getJSONArray("track");
            for (int i = 0; i < tracks.length(); i++) {
                JSONObject track = tracks.getJSONObject(i);
                String trackName = track.getString("name");
                String trackUrl = track.getString("url");
                JSONObject artistObj = track.getJSONObject("artist");
                String artistName = artistObj.getString("name");
                String artistUrl = artistObj.getString("url");
                String smaallimageUrl = "";
                String mediumimageUrl = "";
                String largeimageUrl = "";
                String extralargeimageUrl = "";
                int rank = 0;
                try {
                    JSONArray imageUrls = track.getJSONArray("image");
                    for (int j = 0; j < imageUrls.length(); j++) {
                        JSONObject imageObj = imageUrls.getJSONObject(j);
                        if (imageObj.getString("size").equals("small")) {
                            smaallimageUrl = imageObj.getString("#text");
                        } else if (imageObj.getString("size").equals("medium")) {
                            mediumimageUrl = imageObj.getString("#text");
                        } else if (imageObj.getString("size").equals("large")) {
                            largeimageUrl = imageObj.getString("#text");
                        } else if (imageObj.getString("size").equals("extralarge")) {
                            extralargeimageUrl = imageObj.getString("#text");
                        }
                    }
                    JSONObject atrr = track.getJSONObject("@attr");
                    rank = atrr.getInt("rank");
                } catch (Exception e) {
                    smaallimageUrl = "";
                    mediumimageUrl = "";
                    largeimageUrl = "";
                    extralargeimageUrl = "";
                }

                Track t = new Track(trackName, trackUrl, artistName, artistUrl, smaallimageUrl);
                t.setImagemediumurl(mediumimageUrl);
                t.setRank(rank);
                this.tracks.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

