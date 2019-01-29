package park.test.wikisovereignflags;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FlagsAdapter extends ArrayAdapter<SovereignFlag> {

    ArrayList<SovereignFlag> flags = new ArrayList<SovereignFlag>();
    Activity activity;

    public FlagsAdapter(Activity activity, ArrayList<SovereignFlag> flags) {
        super(activity, R.layout.list_flag_item, flags);
        this.flags = flags;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FlagViewHolder flagViewHolder = null;
        SovereignFlag flag = flags.get(position);

        View itemView = convertView;

        if (itemView == null) {
            flagViewHolder = new FlagViewHolder();
            itemView = activity.getLayoutInflater().inflate(R.layout.list_flag_item, parent, false);
            flagViewHolder.imageView = (ImageView) itemView.findViewById(R.id.flagimage);
            flagViewHolder.flagkorname = (TextView) itemView.findViewById(R.id.flagkorname);
            flagViewHolder.flagcode = (TextView) itemView.findViewById(R.id.flagcode);
            flagViewHolder.flagname = (TextView) itemView.findViewById(R.id.flagname);
            flagViewHolder.flagshortname = (TextView) itemView.findViewById(R.id.flagshortname);
            itemView.setTag(flagViewHolder);
        } else {
            flagViewHolder = (FlagViewHolder) itemView.getTag();
        }

        flagViewHolder.imageView.setImageResource(flag.getRid());
        flagViewHolder.flagkorname.setText(flag.getKorname());
        flagViewHolder.flagcode.setText(flag.getCode());
        flagViewHolder.flagname.setText(flag.getName());
        flagViewHolder.flagshortname.setText(flag.getShortname());

        return itemView;
    }

    class FlagViewHolder {
        public ImageView imageView;
        public TextView flagkorname;
        public TextView flagcode;
        public TextView flagname;
        public TextView flagshortname;
    }

}
