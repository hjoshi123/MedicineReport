package me.blume.medicinereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LauncherActivity extends AppCompatActivity {
	private ImageButton mDrugReport;
	private ImageView mDrugInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		mDrugReport = findViewById(R.id.drug_report);
		mDrugInfo = findViewById(R.id.drug_info);

		mDrugReport.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});

		mDrugInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(LauncherActivity.this, QRActivity.class);
				startActivity(intent);
			}
		});
	}
}
