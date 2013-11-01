package com.hornets.kplanner.fragments;

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
		NumberPicker np = (NumberPicker) dialogView.findViewById(R.id.numberPicker);
		np.setMinValue(0);
        np.setMaxValue(50);
       np.setValue(30);
		
	}
	

}
