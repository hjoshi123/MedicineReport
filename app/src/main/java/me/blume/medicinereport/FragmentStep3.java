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
public class FragmentStep3 extends Fragment {
	private EditText mDrugName, mLabelledStrength, mManufacturer, mDose, mFreq, mRoute, mDiagnosis, mLotNo, mExpiry, mHerbalTherapy;
	private CheckBox mYes, mNo, mNA;
	private OnFragmentInteractionListener mListener;

	public FragmentStep3() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_fragment_step3, container, false);
		mDrugName = v.findViewById(R.id.input_drug_name);
		mLabelledStrength = v.findViewById(R.id.input_stength);
		mManufacturer = v.findViewById(R.id.input_manufac);
		mDose = v.findViewById(R.id.input_dose);
		mFreq = v.findViewById(R.id.input_freq);
		mRoute = v.findViewById(R.id.input_route);
		mDiagnosis = v.findViewById(R.id.input_diag);
		mLotNo = v.findViewById(R.id.input_lot);
		mExpiry = v.findViewById(R.id.input_exp_date);
		mHerbalTherapy = v.findViewById(R.id.input_herbal);
		mYes = v.findViewById(R.id.yes);
		mNo = v.findViewById(R.id.no);
		mNA = v.findViewById(R.id.na);
		return v;
	}

	@Override
	public void onAttach(Context context) {
		Log.d("ak", "attach works");
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
		Log.d("chunga", "detach works");
		Bundle bundle = new Bundle();
		Log.d("chunga", mDrugName.getEditableText().toString());
		bundle.putString("drug_name", mDrugName.getEditableText().toString());
		bundle.putString("strength", mLabelledStrength.getEditableText().toString());
		bundle.putString("route", mRoute.getEditableText().toString());
		bundle.putString("manufac", mManufacturer.getEditableText().toString());
		bundle.putString("dose", mDose.getEditableText().toString());
		bundle.putString("mFreq", mFreq.getEditableText().toString());
		bundle.putString("diagnosis", mDiagnosis.getEditableText().toString());
		bundle.putString("lotno", mLotNo.getEditableText().toString());
		bundle.putString("expiry", mExpiry.getEditableText().toString());
		bundle.putString("herbal", mHerbalTherapy.getEditableText().toString());

		String eventAbate = "";
		if (mYes.isChecked()) eventAbate += "YES";
		else if (mNo.isChecked()) eventAbate += "NO";
		else if (mNA.isChecked()) eventAbate += "NA";

		bundle.putString("event_abate", eventAbate);
		mListener.onFragmentInteraction3(bundle);

		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction3(Bundle bundle);
	}


}
