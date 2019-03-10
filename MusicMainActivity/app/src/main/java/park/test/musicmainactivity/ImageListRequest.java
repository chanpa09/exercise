package park.test.musicmainactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImageListRequest {
    private Map<String, Bitmap> images;
    String ss = "";

    public ImageListRequest() {
        images = Collections
                .synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));
    }

    public synchronized Bitmap loadImage(String smallImageurl) {
        if (images.containsKey(smallImageurl)) {
            return images.get(smallImageurl);
        } else {
            new ImageRequestAsync().execute(smallImageurl);
            return null;
        }
    }

    class ImageRequestAsync extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            ss = params[0];
            URL url = null;
            Bitmap bmp = null;
            try {
                url = new URL(ss);
                Log.i("ImageRequestAsync", "-----------------------------------------" + ss);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (this) {
                images.put(ss, bmp);
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            super.onPostExecute(bmp);
        }
    }
}
