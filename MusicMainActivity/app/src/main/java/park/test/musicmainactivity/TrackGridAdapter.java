package park.test.musicmainactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrackGridAdapter extends ArrayAdapter<Track> {
    Activity context;
    ArrayList<Track> tracks = new ArrayList<Track>();
    ImageListRequest ira;

    public TrackGridAdapter(Activity context, ArrayList<Track> values, ImageListRequest ira) {
        super(context, R.layout.track_grid_item, values);
        this.context = context;
        this.tracks = values;
        this.ira = ira;
    }

    public int getCount() {
        return tracks.size();
    }

    public Track getItem(int position) {
        return tracks.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            TrackGridViewHolder holder;
            LayoutInflater
                    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView != null) {
                holder = (TrackGridViewHolder) convertView.getTag();
            } else {
                holder = new TrackGridViewHolder(convertView = inflater.inflate(R.layout.track_grid_item, null));
            }
            if (tracks.get(position).getImagesmallurl() != null) {
                Bitmap bmp = ira.loadImage(tracks.get(position).getImagemediumurl());
                if (bmp != null) {
                    holder.imageview.setImageBitmap(bmp);
                } else {
                    holder.imageview.setImageResource(R.drawable.empty);
                }
            } else {
                holder.imageview.setImageResource(R.drawable.empty);
            }
            convertView.setOnClickListener(new GridListener(
                    tracks.get(position).getTrackurl(),
                    tracks.get(position).getArtistname(),
                    tracks.get(position).getTrackname(),
                    tracks.get(position).getImagemediumurl()));
            holder.textView.setText(tracks.get(position).getRank() + "." + tracks.get(position).getTrackname());
            if ((position) % (MusicUtil.LINE / 5) == 0) {
                this.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    public class GridListener implements View.OnClickListener {
        String trackurl = "";
        String artistname;
        String trackname;
        String large;

        public GridListener(String trackurl, String artistname, String trackname, String large) {
            this.trackurl = trackurl;
            this.artistname = artistname;
            this.trackname = trackname;
            this.large = large;
        }

        @Override
        public void onClick(View v) {
            TrackGridViewHolder holder = (TrackGridViewHolder) v.getTag();
            String sf = String.format("%s", artistname + "\'s  " + trackname);
            alert(sf);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trackurl));
            context.startActivity(intent);
        }
    }

    public void alert(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}

class TrackGridViewHolder {
    public ImageView imageview;
    public TextView textView;

    public TrackGridViewHolder(View view) {
        imageview = (ImageView) view.findViewById(R.id.imageView);
        textView = (TextView) view.findViewById(R.id.textView);
        view.setTag(this);
    }
}