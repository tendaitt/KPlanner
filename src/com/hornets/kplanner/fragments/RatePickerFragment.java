package com.hornets.kplanner.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.hornets.kplanner.R;
/**
 * 
 * @author Tendai T.T Mudyiwa
 * @version November 24 2013
 *
 *RatePickerFragment implements the rate picker dialog for the KPlanner
 */
public class RatePickerFragment extends DialogFragment {

	private EditText rateEditText;
	
	/**
	 * 
	 * @author Tendai T.T. Mudyiwa
	 * @version November 24 2013
	 *IRatePickerListener is required to implement the 
	 *RatePickerFragment
	 */
	public interface IRatePickerListener {
        public void onRateSet(String rate);
        public void onRateCancel(DialogFragment dialog);
    }
    
    // Use this instance of the interface to deliver action events
    IRatePickerListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the IHourPickerListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the IHourPickerListener so we can send events to the host
            mListener = (IRatePickerListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement IHourPickerListener");
        }
    }
    
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceBundle){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.rate_dialog, null);
		rateEditText = (EditText) dialogView.findViewById(R.id.rate_value);
		builder.setView(dialogView)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				
				
				mListener.onRateSet(rateEditText.getText().toString());
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				RatePickerFragment.this.getDialog().cancel();
			}
		}).setTitle(R.string.enterRate);      
		return builder.create();
	}
}
