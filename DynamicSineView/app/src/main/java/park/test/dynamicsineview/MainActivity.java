package park.test.dynamicsineview;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
        setContentView(new DynamicSineView2(this));
    }
}
