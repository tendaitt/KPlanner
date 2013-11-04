package com.hornets.kplanner.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.hornets.kplanner.R;

public class HourPickerFragment extends DialogFragment {

	private NumberPicker np;
	 /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface IHourPickerListener {
        public void onHourSet(int i);
        public void onHourCancel(DialogFragment dialog);
    }
    
    // Use this instance of the interface to deliver action events
    IHourPickerListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the IHourPickerListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the IHourPickerListener so we can send events to the host
            mListener = (IHourPickerListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement IHourPickerListener");
        }
    }
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		//Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.fragment_numberpicker, null);
		setHourPicker(dialogView);
		//Inflate and set layout for the dialog
		//Pass null as the parent view because it's going in the dialog layout
		builder.setView(dialogView)
		//Add action buttons
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				
				mListener.onHourSet(np.getValue());
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				HourPickerFragment.this.getDialog().cancel();
			}
		}).setTitle(R.string.pickHours);      
		return builder.create();
	}

	private void setHourPicker(View dialogView) {
		np = (NumberPicker) dialogView.findViewById(R.id.numberPicker);
		np.setMinValue(0);
        np.setMaxValue(50);
       np.setValue(15);
		
	}
	

}
