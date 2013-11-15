package com.hornets.kplanner.activities;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hornets.kplanner.R;
import com.hornets.kplanner.fragments.HourPickerFragment;
import com.hornets.kplanner.fragments.RatePickerFragment;

@SuppressLint("ShowToast")
public class IncomeActivity extends FragmentActivity implements HourPickerFragment.IHourPickerListener, RatePickerFragment.IRatePickerListener{
	private RadioButton onCampusRadioButton;
	private RadioButton offCampusRadioButton;
	private RadioButton otherIncomeButton;
	private TextView hourView;
	private TextView rateView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);
		// Show the Up button in the action bar.
		setupActionBar();
		onCampusRadioButton = (RadioButton) findViewById(R.id.onCampusIncomeButton);
		offCampusRadioButton = (RadioButton) findViewById(R.id.offCampusIncomeButton);
		otherIncomeButton = (RadioButton) findViewById(R.id.otherIncomeButton);
		hourView = (TextView) findViewById(R.id.incomeHoursView);
		rateView = (TextView) findViewById(R.id.ratesView);
		
		
		
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
	
	public void enterRate(View view) {
		RatePickerFragment ratePicker = new RatePickerFragment();
		ratePicker.show(getSupportFragmentManager(), "rate_dialog");
		
	}
	@Override
	public void onHourSet(int value) {
		hourView.setText(""+value);
	}
	@Override
	public void onHourCancel(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onRateSet(String rate) {
		rateView.setText(rate);
	}
	@Override
	public void onRateCancel(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
	
	public void onSave(View view) {		
		
		if(onCampusRadioButton.isChecked()){
			updateDB("ON");
		}
		else if (offCampusRadioButton.isChecked()) {
			updateDB("OFF");
		}
		else if (otherIncomeButton.isChecked()){
			updateDB("OTHER");
		}
		else{
			Toast toast = Toast.makeText(getApplicationContext(),"Please check an income type!", 4);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show();
		}
		
	}
	
	public void updateDB(String type){
		//retrive the values
		String hours = hourView.getText().toString();
		String rate = rateView.getText().toString();
		
		
		
//		//get the values for everything
//				tableNumber = tableID.getText().toString();
//				seatNumber = seatID.getText().toString();
//				meal = menuSpinner.getSelectedItem().toString();
//
//				//update database
//				PatronDbSQLHelper dbHelper = new PatronDbSQLHelper(this.getApplicationContext(), meal, null, 0);
//				SQLiteDatabase patronDB = dbHelper.getWritableDatabase();
//				//map of values
//				ContentValues values = new ContentValues();
//				values.put(PatronEntry.COLUMN_TABLE_ID, tableNumber);
//				values.put(PatronEntry.COLUMN_MEAL, meal);
//				values.put(PatronEntry.COLUMN_SEAT_ID, seatNumber);
//
//				//Insert the new row
//				@SuppressWarnings("unused")
//				long newRowId;
//				newRowId = patronDB.insert(PatronEntry.TABLE_NAME, null, values);


	}

}
