package park.test.wikisovereignflags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SovereignFlag> manyflags = new ArrayList<SovereignFlag>();
    ListView list;
    ImageView bigflag;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("国家　国旗");
        list = (ListView) findViewById(R.id.listView);
        bigflag = (ImageView) findViewById(R.id.bigflag);
        textView = (TextView) findViewById(R.id.textView);

        manyflags = readFlags();

        ArrayAdapter<SovereignFlag> adapter = new FlagsAdapter(this, manyflags);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                SovereignFlag selectedflag = manyflags.get(position);
                String message = "You clicked position " + position + " Sovereign is " + selectedflag.getName();
                bigflag.setImageResource(selectedflag.getRid());
                textView.setText(selectedflag.getKorname() + " " + selectedflag.getShortname());
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    public ArrayList<SovereignFlag> readFlags() {
        ArrayList<SovereignFlag> flags = new ArrayList<SovereignFlag>();

        flags.clear();
        flags.add(new SovereignFlag("Japan", "JP", "392", "日本", R.drawable.jp));
        flags.add(new SovereignFlag("Republic of Korea", "KR", "410", "韓国", R.drawable.kr));

        return flags;
    }
}
