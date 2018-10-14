package me.blume.medicinereport;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Step4 extends Fragment {
	private OnFragmentInteractionListener mListener;
	private EditText mClinicianName, mClinicAddress, mClinicianPin, mTelephoneNo, mSpeciality, mReporterNA, mReporterPhone, mReporterOccupation;
    private RadioGroup mRadioGroup;
	public Step4() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_step4, container, false);
		mClinicianName = view.findViewById(R.id.input_clinic_title);
		mClinicAddress = view.findViewById(R.id.input_clinic_address);
		mClinicianPin = view.findViewById(R.id.input_pincode);
		mTelephoneNo = view.findViewById(R.id.input_telephone);
		mSpeciality = view.findViewById(R.id.input_speciality);
		mReporterNA = view.findViewById(R.id.input_report_title);
		mReporterPhone = view.findViewById(R.id.input_reporter_phone);
		mReporterOccupation = view.findViewById(R.id.occupation);
		mRadioGroup = view.findViewById(R.id.radioProf);
		return view;
	}

	@Override
	public void onAttach(Context context) {
		Log.d("mada", "attach works");
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
		Log.d("mada", "detach works");
		Bundle bundle = new Bundle();
		String prof = "";
        int selectedId = mRadioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.radioNo) {
            prof = "0";
        } else if (selectedId == R.id.radioYes) {
            prof = "1";
        }

        bundle.putString("clinic_name", mClinicianName.getEditableText().toString());
		bundle.putString("clinic_address", mClinicAddress.getEditableText().toString());
		bundle.putString("clinic_pin", mClinicianPin.getEditableText().toString());
		bundle.putString("clinic_tele", mTelephoneNo.getEditableText().toString());
		bundle.putString("speciality", mSpeciality.getEditableText().toString());
		bundle.putString("reporter_addr", mReporterNA.getEditableText().toString());
		bundle.putString("reporter_phone", mReporterPhone.getEditableText().toString());
		bundle.putString("reporter_occu", mReporterOccupation.getEditableText().toString());
		bundle.putString("health_prof", prof);
		mListener.onFragmentInteraction4(bundle);

		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction4(Bundle bundle);
	}

}
