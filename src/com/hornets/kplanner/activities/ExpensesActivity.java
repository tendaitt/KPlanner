package com.hornets.kplanner.activities;




import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.hornets.kplanner.R;
import com.hornets.kplanner.fragments.DatePickerFragment;

/**
 * 
 * @author Mehmet Kologlu
 * @version October 24 2013
 *
 */

public class ExpensesActivity extends FragmentActivity
implements DatePickerFragment.DatePickerListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expenses, menu);
		return true;
	}
	
	public void showDatePickerDialog(View v){
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.show(getSupportFragmentManager(), "datePicker");
	}

	@Override
	public void onDateSet(DialogFragment dialog, int year, int month, int day) {
		// TODO Auto-generated method stub
		Button pickADateBtn = (Button) findViewById(R.id.expenses_button_datepicker);
		pickADateBtn.setText(month + "/" + day + "/" + year);
	}

}
