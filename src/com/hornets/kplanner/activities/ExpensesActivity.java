package com.hornets.kplanner.activities;

import java.util.Calendar;

import com.hornets.kplanner.R;
import com.hornets.kplanner.fragments.DatePickerFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * 
 * @author Mehmet Kologlu
 * @version October 28 2013
 *
 */

public class ExpensesActivity extends FragmentActivity
implements DatePickerFragment.DatePickerDialogListener{

	Button dateBtn;
	int year;
	int month;
	int day;
	LinearLayout reminderLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);

		//get current date
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
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
		
		reminderLinearLayout = (LinearLayout) findViewById(R.id.expneses_linearayout_reminderentry);
		
		if(((Switch) findViewById(R.id.expenses_switch_reminder)).isChecked()){
			//switch is turned on
			
			//INFALTE REMINDER INPUT LAYOUT ELEMENTS
			
			EditText et = new EditText(this);
			et.setHint("asd");
			reminderLinearLayout.addView(et);
			reminderLinearLayout.invalidate();
			
		}
//		else //switch is turned off
			
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
