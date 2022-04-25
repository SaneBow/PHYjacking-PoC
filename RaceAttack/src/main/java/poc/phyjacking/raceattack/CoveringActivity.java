package poc.phyjacking.raceattack;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


public class CoveringActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.covering);
        View screen = (View) findViewById(R.id.screenFrame);
        screen.setBackgroundColor(Color.RED);
        screen.setAlpha(0.5f);
    }
}
