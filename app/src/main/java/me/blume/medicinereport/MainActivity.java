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

public class MainActivity extends AppCompatActivity implements FragmentStep1.OnFragmentInteractionListener, FragmentStep2.OnFragmentInteractionListener, FragmentStep3.OnFragmentInteractionListener {
	private Fragment mFragmentStep1 = new FragmentStep1();
	private Fragment mFragmentStep2 = new FragmentStep2();
	private Fragment mFragmentStep3 = new FragmentStep3();
	private Fragment mFragmentStep4 = new Step4();
 	private String mFirstName, mLastName, mSex, mWeight, mDob, mOutcomes, mStartDate,
			mEndDate, mLabTest, mCondition, mDescCondition, mDateOfDeath, mOther, mDrugName,
			mLabelledStrength, mManufacturer, mDose, mFreq, mRoute, mDiagnosis, mLotNo, mExpiry,
			mHerbalTherapy, mEventAbate;
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
		fragments.add(mFragmentStep4);

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
				if(i == 0) {
					Toast.makeText(MainActivity.this, "First Fragment", Toast.LENGTH_SHORT).show();
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
				compileFinalLinked();
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

		compileFinalLinked();
	}

	@Override
	public void onFragmentInteraction2(Bundle bundle) {
		Log.d("ak", "frag interact works 2 works");

		mOutcomes = bundle.getString("outcomes");
		Log.d("ak", mOutcomes);
		mDescCondition = bundle.getString("desc");
		mStartDate = Encode.trimDate(bundle.getString("start_date"));
		mEndDate = Encode.trimDate(bundle.getString("end_date"));
		mLabTest = bundle.getString("lab");
		mCondition = bundle.getString("pre");
		mDateOfDeath = Encode.trimDate(bundle.getString("death_date"));
		Log.d("ak", mDateOfDeath);
		mOther = bundle.getString("adverse");

		LinkedList<String> outcomeList = new LinkedList<>();
		outcomeList.add(mOutcomes);
		outcomeList.add(mDateOfDeath);
		outcomeList.add(mOther);
		Log.d("ak", outcomeList.toString());
		mOutcomes = Encode.getHyphenCat(outcomeList);
		Log.d("ak", "outcome to be submitted "+mOutcomes);

		compileFinalLinked();
	}

	@Override
	public void onFragmentInteraction3(Bundle bundle) {
		Log.d("ak", "frag interact works 3 works");
		mDrugName = bundle.getString("drug_name");
		mLabelledStrength = bundle.getString("strength");
		mRoute = bundle.getString("route");
		mDiagnosis = bundle.getString("diagnosis");
		mLotNo = bundle.getString("lotno");
		mExpiry = bundle.getString("expiry");
		mHerbalTherapy = bundle.getString("herbal");
		mManufacturer = bundle.getString("manufac");
		mDose = bundle.getString("dose");
		mFreq = bundle.getString("mFreq");
		mEventAbate = Encode.getStatus(bundle.getString("event_abate"));

		LinkedList<String> drugList = new LinkedList<>();
		drugList.add(mDrugName);
		drugList.add(mLabelledStrength);
		drugList.add(mManufacturer);
		mDrugName = Encode.getHyphenCat(drugList);

		drugList = new LinkedList<>();
		drugList.add(mDose);
		drugList.add(mFreq);
		drugList.add(mRoute);
		mDose = Encode.getHyphenCat(drugList);

		drugList = new LinkedList<>();
		drugList.add("090818");
		drugList.add("061018");
		mFreq = Encode.getHyphenCat(drugList);

		drugList = new LinkedList<>();
		drugList.add(mLotNo);
		drugList.add(mExpiry);
		mLotNo = Encode.getHyphenCat(drugList);


		compileFinalLinked();
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}

	public void compileFinalLinked(){
		//Fragment 1
		finalLinked.clear();
		finalLinked.add(mFirstName);
		finalLinked.add(mLastName);
		finalLinked.add(mDob);
		finalLinked.add(mSex);
		finalLinked.add(mWeight);

		//Fragment 2
		finalLinked.add(mOutcomes);
		finalLinked.add(mStartDate);
		finalLinked.add(mEndDate);
		finalLinked.add(mDescCondition);
		finalLinked.add(mLabTest);
		finalLinked.add(mCondition);

		//Fragment 3
		finalLinked.add(mDrugName);
		finalLinked.add(mDose);
		finalLinked.add(mFreq);
		finalLinked.add(mDiagnosis);
		finalLinked.add(mEventAbate);
		finalLinked.add(mLotNo);
		finalLinked.add("2");
		finalLinked.add(mHerbalTherapy);



		Log.d("LIT List", finalLinked.toString());
	}


}
