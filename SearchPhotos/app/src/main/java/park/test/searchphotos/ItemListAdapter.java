package park.test.searchphotos;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemListAdapter extends ArrayAdapter<Item> implements View.OnClickListener {

    Activity context;
    ArrayList<Item> items;
    ImageListRequest ira;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

    public ItemListAdapter(Activity context, ArrayList<Item> items, ImageListRequest ira) {
        super(context, R.layout.list_item, items);
        this.context = context;
        this.items = items;
        this.ira = ira;
    }

    public int getCount() {
        return items.size();
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            final ItemListViewHoler holer;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView != null) {
                holer = (ItemListViewHoler) convertView.getTag();
            } else {
                holer = new ItemListViewHoler(convertView = inflater.inflate(R.layout.list_item, null));
            }

            if (items.get(position).getSmallImageURL() != null) {
                Bitmap bmp = ira.loadImage(items.get(position).getSmallImageURL());

                if (bmp != null) {
                    holer.imageView.setImageBitmap(bmp);
                } else {
                    holer.imageView.setImageResource(R.drawable.empty);
                }
            } else {
                holer.imageView.setImageResource(R.drawable.empty);
            }

            convertView.setOnClickListener(this);
            holer.textView.setText(items.get(position).getTitle());
            this.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }


    @Override
    public void onClick(View v) {

    }

}

class ItemListViewHoler {
    public ImageView imageView;
    public TextView textView;

    public ItemListViewHoler(View view) {
        imageView = (ImageView) view.findViewById(R.id.resultImageView);
        textView = (TextView) view.findViewById(R.id.resultTitle);
    }
}
