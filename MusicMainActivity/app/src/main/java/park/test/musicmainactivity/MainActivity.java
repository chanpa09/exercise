package park.test.musicmainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton trackListBtn;
    ImageButton trackGridBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("J4AMusic");
        trackListBtn = (ImageButton) this.findViewById(R.id.trackListBtn);
        trackGridBtn = (ImageButton) this.findViewById(R.id.trackGridBtn);

        trackListBtn.setOnClickListener(new MusicListener());
        trackGridBtn.setOnClickListener(new MusicListener());
    }

    //Nested Class- EHO
    class MusicListener implements View.OnClickListener {
        Intent intent;

        public void onClick(View v) {
            if (v.getId() == R.id.trackListBtn) {
                intent = new Intent(MainActivity.this,
                        ToptrackListActivity.class);
            } else if (v.getId() == R.id.trackGridBtn) {
                intent = new Intent(MainActivity.this,
                        TopTrackGridActivity.class);
            }
            startActivity(intent);
        }
    }
}
