package com.hornets.kplanner.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.hornets.kplanner.R;
import com.hornets.kplanner.fragments.HourPickerFragment;

public class IncomeActivity extends FragmentActivity {
	private RadioButton onCampusRadioButton;
	private RadioButton offCampusRadioButton;
	private RadioButton otherIncomeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);
		// Show the Up button in the action bar.
		setupActionBar();
		onCampusRadioButton = (RadioButton) findViewById(R.id.onCampusIncomeButton);
		offCampusRadioButton = (RadioButton) findViewById(R.id.offCampusIncomeButton);
		otherIncomeButton = (RadioButton) findViewById(R.id.otherIncomeButton);
		
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.income, menu);
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
	
	public void onCampus(View view){
		offCampusRadioButton.setChecked(false);
		otherIncomeButton.setChecked(false);
	}
	
	public void offCampus(View view){
		onCampusRadioButton.setChecked(false);
		otherIncomeButton.setChecked(false);
	}
	
	public void otherIncome(View view){
		onCampusRadioButton.setChecked(false);
		offCampusRadioButton.setChecked(false);
	}
	
	public void onHourClicked(View view){
		HourPickerFragment hourPicker = new HourPickerFragment();
		hourPicker.show(getSupportFragmentManager(), "hourpicker");
	}

}
