package com.hornets.kplanner;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	
	public interface DatePickerListener{
		public void onDateSet(DialogFragment dialog, int year, int month, int day);
	}
	
	DatePickerListener mListener;

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		//Verify that the host activity implements the callback interface
		try{
			//Instantiate the EnterFileNameDialogListener so we can send events to the host
			mListener = (DatePickerListener) activity;
		}
		catch(ClassCastException e) {
			// the activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
				+ " must implement EnterFileNameSDialogListener");
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
	}
}
