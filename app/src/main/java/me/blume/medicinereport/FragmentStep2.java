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
	private CheckBox mDeath, mLifeThreat, mHos, mCogen, mOther;
	private EditText mAdverse;

	public FragmentStep2() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_fragment_step2, container, false);
		mDeath = view.findViewById(R.id.death);
		mLifeThreat = view.findViewById(R.id.life);
		mHos = view.findViewById(R.id.hospital);
		mCogen = view.findViewById(R.id.cogen);
		mOther = view.findViewById(R.id.other);
		mAdverse = view.findViewById(R.id.adverse);

		if (mOther.isChecked()) {
			mAdverse.setVisibility(View.VISIBLE);
		}

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

		mListener.onFragmentInteraction2(bundle);

		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction2(Bundle bundle);
	}

}
