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


/**
 * A simple {@link Fragment} subclass.
 */
public class Step4 extends Fragment {
	private OnFragmentInteractionListener mListener;
	private EditText mClinicianNA, mClinicianPin, mTelephoneNo, mSpeciality, mReporterNA, mReporterPhone, mReporterOccupation;

	public Step4() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_step4, container, false);
		mClinicianNA = view.findViewById(R.id.input_clinic_title);
		mClinicianPin = view.findViewById(R.id.input_pincode);
		mTelephoneNo = view.findViewById(R.id.input_telephone);
		mSpeciality = view.findViewById(R.id.input_speciality);
		mReporterNA = view.findViewById(R.id.input_report_title);
		mReporterPhone = view.findViewById(R.id.input_reporter_phone);
		mReporterOccupation = view.findViewById(R.id.occupation);
		return view;
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
		bundle.putString("clinic_addr", mClinicianNA.getEditableText().toString());
		bundle.putString("clinic_pin", mClinicianPin.getEditableText().toString());
		bundle.putString("clinic_tele", mTelephoneNo.getEditableText().toString());
		bundle.putString("speciality", mSpeciality.getEditableText().toString());
		bundle.putString("reporter_addr", mReporterNA.getEditableText().toString());
		bundle.putString("reporter_phone", mReporterPhone.getEditableText().toString());
		bundle.putString("reporter_occu", mReporterOccupation.getEditableText().toString());
		mListener.onFragmentInteraction4(bundle);

		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction4(Bundle bundle);
	}

}
