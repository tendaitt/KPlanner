package com.hornets.kplanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.DatePicker;

public class ExpensesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);
		

		//get rid of the calendar view of the DatePicker
		DatePicker dp = (DatePicker) findViewById(R.id.expenses_datePicker);
		dp.setCalendarViewShown(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expenses, menu);
		return true;
	}
}
