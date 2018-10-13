package me.blume.medicinereport;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStep1 extends Fragment {
	private AppCompatEditText mFName, mLName, mWeight;
	private OnFragmentInteractionListener mListener;
	private String mGender;
	private RadioGroup mRadioGroup;

	public FragmentStep1() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.fragment_fragment_step1, container, false);
		mFName = view.findViewById(R.id.fname);
		mLName = view.findViewById(R.id.lname);
		mRadioGroup = view.findViewById(R.id.radioSex);
		mWeight= view.findViewById(R.id.weight);

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
		Log.d("Fuck", "Hi there " + mFName.getEditableText().toString());
		bundle.putString("fName",mFName.getEditableText().toString());
		bundle.putString("lName",mLName.getEditableText().toString());

		int selectedId = mRadioGroup.getCheckedRadioButtonId();

		// find the radiobutton by returned id
		if (selectedId == R.id.radioMale) {
			mGender = "0";
		} else if (selectedId == R.id.radioFemale) {
			mGender = "0";
		}
		bundle.putString("gender", mGender);
		bundle.putString("weight",mWeight.getEditableText().toString());
		mListener.onFragmentInteraction1(bundle);

		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction1(Bundle bundle);
	}

	public void onButtonPressed(Bundle bundle) {
		if (mListener != null) {
			mListener.onFragmentInteraction1(bundle);
		}
	}

}
