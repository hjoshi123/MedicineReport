package me.blume.medicinereport;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStep1 extends Fragment {
	private AppCompatEditText mFName, mLName, mWeight, mDate;
	private OnFragmentInteractionListener mListener;
	private String mGender, mDOB;
	private RadioGroup mRadioGroup;

	public FragmentStep1() {
		// Required empty public constructor
	}

	Calendar myCalendar = Calendar.getInstance();

	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
		                      int dayOfMonth) {
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}

	};


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		Log.d("STEP","oncreateview works");
		View view =  inflater.inflate(R.layout.fragment_fragment_step1, container, false);
		mFName = view.findViewById(R.id.fname);
		mLName = view.findViewById(R.id.lname);
		mRadioGroup = view.findViewById(R.id.radioSex);
		mWeight= view.findViewById(R.id.weight);
		mDate = view.findViewById(R.id.date);

		mDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DatePickerDialog(getActivity(), date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		return view;
	}

	@Override
	public void onAttach(Context context) {
		Log.d("STEP","attach works");
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	private void updateLabel() {
		String myFormat = "dd/MM/yy"; //In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		mDate.setText(sdf.format(myCalendar.getTime()));
		mDOB = sdf.format(myCalendar.getTime());
		Log.d("Hello", mDOB);
	}

	@Override
	public void onDetach() {
		Log.d("STEP","detach works");
		Bundle bundle = new Bundle();
		Log.d("Fuck", "Hi there " + mFName.getEditableText().toString());
		bundle.putString("fName",mFName.getEditableText().toString());
		bundle.putString("lName",mLName.getEditableText().toString());

		int selectedId = mRadioGroup.getCheckedRadioButtonId();

		// find the radiobutton by returned id
		if (selectedId == R.id.radioMale) {
			mGender = "0";
		} else if (selectedId == R.id.radioFemale) {
			mGender = "1";
		}
		bundle.putString("gender", mGender);
        bundle.putString("dob", mDOB);
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
