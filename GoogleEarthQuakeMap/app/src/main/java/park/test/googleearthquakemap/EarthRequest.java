package park.test.googleearthquakemap;

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

public class EarthRequest {

    ArrayList<EarthQuake> earthquakes = new ArrayList<>();
    boolean isConnection = false;

    public boolean isConnection() {
        return isConnection;
    }

    public ArrayList<EarthQuake> getEarthquakes() {
        return earthquakes;
    }

    public void getEarth(String newUrls) {
        InputStream inputStream;
        URL url = null;

        try {
            url = new URL(newUrls);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
            inputStream.close();

            parseTrackToJson(sb.toString());
            isConnection = true;
        } catch (Exception e) {
            isConnection = false;
        }
    }

    private void parseTrackToJson(String sjson) throws JSONException {
        earthquakes.clear();

        JSONObject jObject = new JSONObject(sjson);
        JSONArray jArray = jObject.getJSONArray("earthquakes");

        try {
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                EarthQuake t = toEarth(json);
                earthquakes.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public EarthQuake toEarth(JSONObject json) throws JSONException {
        EarthQuake earth = null;

        String eqid = json.getString("eqid");

        double magnitude = json.getDouble("magnitude");
        double longitude = json.getDouble("lng");
        double latitude = json.getDouble("lat");
        String source = json.getString("src");
        String datetime = json.getString("datetime");
        double depth = json.getDouble("depth");

        earth = new EarthQuake(eqid, magnitude, longitude, latitude, source, datetime, depth, "");
        return earth;
    }

}
