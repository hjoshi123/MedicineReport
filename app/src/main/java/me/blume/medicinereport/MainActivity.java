package me.blume.medicinereport;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import me.blume.medicinereport.utils.Encode;

public class MainActivity extends AppCompatActivity implements FragmentStep1.OnFragmentInteractionListener, FragmentStep2.OnFragmentInteractionListener {
	private Fragment mFragmentStep1 = new FragmentStep1();
	private Fragment mFragmentStep2 = new FragmentStep2();
	private Fragment mFragmentStep3 = new FragmentStep3();
	private String mFirstName, mLastName, mSex, mWeight, mDob, mOutcomes, mStartDate, mEndDate, mLabTest, mCondition, mDescCondition;
	private FragmentTransaction mFragTrans;
	private Button mNext, mPrevious, mSubmit;
	private LinkedList<String> finalLinked;
	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNext = findViewById(R.id.next);
		mPrevious = findViewById(R.id.back);
		mSubmit = findViewById(R.id.submit);
		finalLinked = new LinkedList<>();


		final ArrayList<Fragment> fragments=new ArrayList<>();
		fragments.add(mFragmentStep1);
		fragments.add(mFragmentStep2);
		fragments.add(mFragmentStep3);

		mFragTrans = getSupportFragmentManager().beginTransaction();
		mFragTrans.replace(R.id.mainframe, fragments.get(0));
		mFragTrans.commit();

		mNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				mFragTrans = getSupportFragmentManager().beginTransaction();
				if(i == fragments.size() - 1) {
					Toast.makeText(MainActivity.this, "Last Fragment", Toast.LENGTH_SHORT).show();
					mSubmit.setVisibility(View.VISIBLE);
				} else {
					mFragTrans.replace(R.id.mainframe,fragments.get(++i));
					mFragTrans.commit();

					if(i == fragments.size() - 1) {
						mNext.setVisibility(View.INVISIBLE);
						mSubmit.setVisibility(View.VISIBLE);
					} else {
						mNext.setVisibility(View.VISIBLE);
						mSubmit.setVisibility(View.GONE);
					}

					if(i == 0)
						mPrevious.setVisibility(View.INVISIBLE);
					else
						mPrevious.setVisibility(View.VISIBLE);
				}
			}
		});

		mPrevious.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				mFragTrans = getSupportFragmentManager().beginTransaction();
				if(i == fragments.size() - 1) {
					Toast.makeText(MainActivity.this, "Last Fragment", Toast.LENGTH_SHORT).show();
				} else {
					mFragTrans.replace(R.id.mainframe,fragments.get(--i));
					mFragTrans.commit();

					if(i == fragments.size()-1) {
						mNext.setVisibility(View.INVISIBLE);
						mSubmit.setVisibility(View.VISIBLE);
					} else {
						mNext.setVisibility(View.VISIBLE);
						mSubmit.setVisibility(View.GONE);
					}

					if(i == 0)
						mPrevious.setVisibility(View.INVISIBLE);
					else
						mPrevious.setVisibility(View.VISIBLE);
				}
			}
		});

		mSubmit.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Entered here", Toast.LENGTH_SHORT).show();
			}
		});
	}


	@Override
	public void onFragmentInteraction1(Bundle bundle) {
		mFirstName = bundle.getString("fName");
		Log.d("MainAc", "En" + mFirstName);
		mLastName = bundle.getString("lName");
		mSex = bundle.getString("gender");
		mWeight = bundle.getString("weight");
		mDob = Encode.trimDate(bundle.getString("dob"));
		mOutcomes = bundle.getString("adverse");
		mDescCondition = bundle.getString("desc");
		mStartDate = bundle.getString("start_date");
		mEndDate = bundle.getString("end_date");
		mLabTest = bundle.getString("lab");
		mCondition = bundle.getString("pre");

		compileFinalLinked();
	}

	@Override
	public void onFragmentInteraction2(Bundle bundle) {
		mOutcomes = bundle.getString("adverse");
		mDescCondition = bundle.getString("desc");
		mStartDate = bundle.getString("start_date");
		mEndDate = bundle.getString("end_date");
		mLabTest = bundle.getString("lab");
		mCondition = bundle.getString("pre");
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}

	public void compileFinalLinked(){
		finalLinked.clear();
		finalLinked.add(mFirstName);
		finalLinked.add(mLastName);
		finalLinked.add(mDob);
		finalLinked.add(mSex);
		finalLinked.add(mWeight);
		Log.d("LIT List", finalLinked.toString());
	}
}
