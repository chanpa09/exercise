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

public class TrackListAdapter extends ArrayAdapter<Track> implements View.OnClickListener {
    Activity context;
    ArrayList<Track> tracks;
    ImageListRequest ira;

    public TrackListAdapter(Activity context, ArrayList<Track> tracks, ImageListRequest ira) {
        super(context, R.layout.track_list_item, tracks);
        this.context = context;
        this.tracks = tracks;
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
            final TrackListViewHolder holder;
            LayoutInflater
                    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView != null) {
                holder = (TrackListViewHolder) convertView.getTag();
            } else {
                holder = new TrackListViewHolder(convertView = inflater.inflate(R.layout.track_list_item, null));
            }

            if (tracks.get(position).getImagemediumurl() != null) {
                Bitmap bmp = ira.loadImage(tracks.get(position).getImagemediumurl());
                if (bmp != null) {
                    holder.billImageView.setImageBitmap(bmp);
                } else {
                    holder.billImageView.setImageResource(R.drawable.empty);
                }
            } else {
                holder.billImageView.setImageResource(R.drawable.empty);
            }
            convertView.setOnClickListener(this);
            holder.billtxtRank.setText(tracks.get(position).getRank() + "");
            holder.trackname.setText(tracks.get(position).getTrackname());
            holder.artistname.setText(tracks.get(position).getArtistname());
            holder.trackurl.setText(tracks.get(position).getTrackurl());
            this.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        TrackListViewHolder holder = (TrackListViewHolder) v.getTag();
        String sf = String.format("%s", holder.artistname.getText() + "\'s  " + holder.trackname.getText());
        alert(sf);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(holder.trackurl.getText() + ""));
        this.context.startActivity(intent);
    }

    public void alert(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}

class TrackListViewHolder {
    public ImageView billImageView;
    public TextView billtxtRank;
    public TextView trackname;
    public TextView artistname;
    public TextView trackurl;

    public TrackListViewHolder(View view) {
        billImageView = (ImageView) view.findViewById(R.id.billImageView);
        billtxtRank = (TextView) view.findViewById(R.id.billtxtRank);
        trackname = (TextView) view.findViewById(R.id.songname);
        artistname = (TextView) view.findViewById(R.id.artistname);
        trackurl = (TextView) view.findViewById(R.id.trackurl);
        view.setTag(this);
    }
}
