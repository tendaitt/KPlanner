package com.hornets.kplanner.activities;

import java.util.Calendar;

import com.hornets.kplanner.R;
import com.hornets.kplanner.fragments.DatePickerFragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

/**
 * 
 * @author Mehmet Kologlu
 * @version October 24 2013
 *
 */

public class ExpensesActivity extends FragmentActivity
implements DatePickerFragment.DatePickerDialogListener{

	Button dateBtn;
	int year;
	int month;
	int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);

		//get current date
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		//define the date button
		dateBtn = (Button) this.findViewById(R.id.expenses_button_datepicker);

		//update the date picker button to display the date which is the current date
		updateDateButtonText();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expenses, menu);
		return true;
	}

	/*
	 * onclick of the pick a date button, 
	 * displays the datepicker fragment
	 */
	public void showDatePickerDialog(View v){
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.show(getSupportFragmentManager(), "datePicker");
	}

	/*
	 * onclick of the reminders switch
	 */
	public void onSwitchClicked(View v) {
		//test
		if(((Switch) findViewById(R.id.expenses_switch_reminder)).isChecked()){
			//switch is turned on
			
			//INFALTE REMINDER INPUT LAYOUT ELEMENTS
			
			((TextView) findViewById(R.id.expenses_text_reminder)).setText(" on "); //test function			
		}
		else //switch is turned off
			
			((TextView) findViewById(R.id.expenses_text_reminder)).setText(" off "); //test function
	}

	/*
	 * onclick of the add button
	 */
	public void onClickAdd(View v) {
		
		//ADD INPUT TO DATABASE

		//RESET
		
	}

	/*
	 * onclick of the done button
	 */
	public void onClickDone(View v) {
		NavUtils.navigateUpFromSameTask(this);
	}

	/*
	 * updates the text of the pick a date button
	 */
	public void updateDateButtonText()
	{
		dateBtn.setText(month + "/" + day + "/" + year);
	}

	/*
	 * (non-Javadoc)
	 * @see com.hornets.kplanner.fragments.DatePickerFragment.DatePickerDialogListener#onDateSelect(android.support.v4.app.DialogFragment, int, int, int)
	 * 
	 */
	@Override
	public void onDateSelect(DialogFragment dialog, int y, int monthOfYear, int dayOfMonth) {
		year = y;
		month = monthOfYear;
		day = dayOfMonth;
		updateDateButtonText();
	}

}
