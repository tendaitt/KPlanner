package com.hornets.kplanner;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

/**
 * 
 * @author Mehmet Kologlu
 * @version October 24 2013
 *
 */

public class ExpensesActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);

		//get rid of the calendar view of the DatePicker
//		DatePicker dp = (DatePicker) findViewById(R.id.expenses_datePicker);
//		dp.setCalendarViewShown(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expenses, menu);
		return true;
	}
	
	public void showDatePickerDialog(View v){
		DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
}
