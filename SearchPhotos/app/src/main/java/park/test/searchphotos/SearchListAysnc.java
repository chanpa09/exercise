package park.test.searchphotos;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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

public class SearchListAysnc extends AsyncTask<String, Void, ArrayList<Item>> {

    Activity activity;
    ProgressBar progressBar;
    boolean isConnection = false;
    ArrayList<Item> items = new ArrayList<>();

    public SearchListAysnc(Activity ac) {
        activity = ac;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBarStart();
    }

    @Override
    protected ArrayList<Item> doInBackground(String... params) {
        InputStream inputStream;
        String jsonString = "";
        String searchInfo = "delicios%20food";
        if(params[0] != null || params[0].length() != 0) searchInfo = params[0];
        String newUrls = ItemUtil.REQUEST_URL + searchInfo;

        try {
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
        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<Item> result) {
        super.onPostExecute(result);
        if (isConnection) {
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).updateItem(result);
            }
            progressBarClose();
        } else {
            progressBarClose();
        }
    }

    private synchronized void parseTrackToJson(String json) throws JSONException {

        items.clear();
        JSONObject jsonObject = new JSONObject(json);
        JSONObject itemObj = jsonObject.getJSONObject("photos");

        try {
            JSONArray itemsJSONarr = itemObj.getJSONArray("photo");

            for (int i = 0; i < itemsJSONarr.length(); i++) {
                JSONObject item = itemsJSONarr.getJSONObject(i);

                String id = item.getString("id");
                String owner = item.getString("owner");
                String secret = item.getString("secret");
                String server = item.getString("server");
                String farm = item.getString("farm");
                String title = item.getString("title");
                String ispublic = item.getString("ispublic");
                String isfriend = item.getString("isfriend");
                String isfamily = item.getString("isfamily");
                String smallImageURL = "http://farm" + farm + ".staticflickr.com/" + server + "/"
                        + id + "_" + secret + "_t.jpg";

                String bigImageURL = "http://farm" + farm + ".staticflickr.com/" + server + "/"
                        + id + "_" + secret + "_b.jpg";

                Item t = new Item(id, owner, secret, server, farm, title, ispublic, isfriend, isfamily, smallImageURL, bigImageURL);
                this.items.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void progressBarStart() {
        progressBar = activity.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void progressBarClose() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
