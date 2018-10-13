package me.blume.medicinereport;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStep2 extends Fragment {
	private OnFragmentInteractionListener mListener;
	private EditText mDeathDate, mAdverse, mStartDate, mEndDate, mDescription, mLabTest, mPreExistingCondition;
	private CheckBox mDeath, mHospital, mCogenital, mLifeThreatening;
	public FragmentStep2() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_fragment_step2, container, false);
		mAdverse = view.findViewById(R.id.adverse);
		mStartDate = view.findViewById(R.id.start_date);
		mEndDate = view.findViewById(R.id.end_date);
		mDescription = view.findViewById(R.id.desc);
		mLabTest = view.findViewById(R.id.test);
		mPreExistingCondition = view.findViewById(R.id.pre);
		mDeathDate = view.findViewById(R.id.deathdate);
		mDeath = view.findViewById(R.id.death);
		mHospital = view.findViewById(R.id.hospital);
		mCogenital = view.findViewById(R.id.cogen);
		mLifeThreatening = view.findViewById(R.id.life);
		return view;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		Bundle bundle = new Bundle();
		String outcome = "";
		if (mDeath.isChecked()) {
			outcome += "0";
		}
		if (mLifeThreatening.isChecked()) {
			outcome += "1";
		}
		if (mHospital.isChecked()) {
			outcome += "2";
		}
		if (mCogenital.isChecked()) {
			outcome += "3";
		}
		bundle.putString("adverse", mAdverse.getEditableText().toString());
		bundle.putString("death_date", mDeathDate.getEditableText().toString());
		bundle.putString("start_date", mStartDate.getEditableText().toString());
		bundle.putString("end_date", mEndDate.getEditableText().toString());
		bundle.putString("desc", mDescription.getEditableText().toString());
		bundle.putString("lab", mLabTest.getEditableText().toString());
		bundle.putString("pre", mPreExistingCondition.getEditableText().toString());
		bundle.putString("outcomes", outcome);
		mListener.onFragmentInteraction2(bundle);

		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction2(Bundle bundle);
	}

}
