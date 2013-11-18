package com.hornets.kplanner.activities;

import java.util.Calendar;

import com.hornets.kplanner.R;
import com.hornets.kplanner.database.KPlannerSQLHelper;
import com.hornets.kplanner.database.KPlannerReaderContract.KPlannerEntry;
import com.hornets.kplanner.fragments.DatePickerFragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/**
 * 
 * @author Mehmet Kologlu
 * @version November 17 2013
 *
 */

public class ExpensesActivity extends FragmentActivity
implements DatePickerFragment.DatePickerDialogListener{

	public static int YEAR;
	public static int MONTH;
	public static int DAY;

	Button dateBtn;
	LinearLayout reminderLinearLayout;
	Switch reminderSwitch;
	EditText edittextName;
	EditText edittextAmount;
	Spinner spinnerType;

	SQLiteDatabase db;

	Calendar c;

	int currentYear;
	int currentMonth;
	int currentDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);

		//initializing the layout elements
		dateBtn = (Button) findViewById(R.id.expenses_button_datepicker);
		reminderSwitch = (Switch) findViewById(R.id.expenses_switch_reminder);
		edittextName = (EditText) findViewById(R.id.expenses_edit_name);
		edittextAmount = (EditText) findViewById(R.id.expenses_edit_amount);
		spinnerType = (Spinner) findViewById(R.id.expenses_spinner_type);

		//initialize & open database
		KPlannerSQLHelper dbHelper = new KPlannerSQLHelper(getApplicationContext(), "EXPENSES", null, 0);
		db = dbHelper.getWritableDatabase();


		//get a calendar
		c = Calendar.getInstance();
		currentYear = c.get(Calendar.YEAR);
		currentMonth = c.get(Calendar.MONTH)+1; //+1 to compensate for 0 indexing
		currentDay = c.get(Calendar.DAY_OF_MONTH);
		resetDate();

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
			db.close();
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
	 * onclick of the add button
	 */
	public void onClickAdd(View v) {

		//ADD INPUT TO DATABASE
		//retrive the values
		String name = edittextName.getText().toString();
		String type = spinnerType.getSelectedItem().toString();
		String amount = edittextAmount.getText().toString();
		String date = Integer.toString(DAY + MONTH + YEAR);

		//map of values
		ContentValues values = new ContentValues();
		values.put(KPlannerEntry.EXPENSE_COLUMN_NAME, name);
		values.put(KPlannerEntry.EXPENSE_COLUMN_TYPE, type);
		values.put(KPlannerEntry.EXPENSE_COLUMN_DATE, date);
		values.put(KPlannerEntry.EXPENSE_COLUMN_AMOUNT, amount);

		//RESET
		resetView();
	}

	/*
	 * onclick of the reminders switch
	 */
	public void onSwitchClicked(View v) {
		//test

		reminderLinearLayout = (LinearLayout) findViewById(R.id.expneses_linearayout_reminderentry);

		if(reminderSwitch.isChecked()){
			//switch is turned on

			//ADD REMINDER INPUT LAYOUT ELEMENTS

			//define elements
			final TextView numberEdit = new TextView(this);
			Button minusButton = new Button(this);
			RadioGroup reminderRadioGroup = new RadioGroup(this);
			Button plusButton = new Button(this);
			final RadioButton daysButton = new RadioButton(this);
			final RadioButton weeksButton = new RadioButton(this);

			//minus button
			minusButton.setText(R.string.expenses_button_minus);
			minusButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v){
					int current = Integer.parseInt(numberEdit.getText().toString());
					//decrease by 1
					if(current != 1){
						//if the value is not 1, decrease it by 1
						current--;
						numberEdit.setText("" + current);
					}
					if(current == 1){
						daysButton.setText(R.string.expeneses_daysButton_radio);
						weeksButton.setText(R.string.expeneses_weeksButton_radio);
					}
				}
			});

			//the integer
			numberEdit.setText(R.string.expenses_edit_reminder);
			numberEdit.setInputType(InputType.TYPE_CLASS_NUMBER);

			//plus button
			plusButton.setText(R.string.expenses_button_plus);
			plusButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v){
					//increase by 1
					int current = Integer.parseInt(numberEdit.getText().toString());
					if(current == 1)
					{
						daysButton.setText("Days");
						weeksButton.setText("Weeks");
					}
					current++;
					numberEdit.setText("" + current);
				}
			});

			//Day & Week Radio Buttons
			reminderRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
			reminderRadioGroup.setPadding(0, 10, 0, 0); //padding so it's in the middle
			daysButton.setText(R.string.expeneses_daysButton_radio);
			weeksButton.setText(R.string.expeneses_weeksButton_radio);
			//add radio buttons to the radio group
			reminderRadioGroup.addView(daysButton);
			reminderRadioGroup.addView(weeksButton);

			//add all views to the layout
			reminderLinearLayout.addView(minusButton);
			reminderLinearLayout.addView(numberEdit);
			reminderLinearLayout.addView(plusButton);
			reminderLinearLayout.addView(reminderRadioGroup);
		}
		else //switch is turned off
		{
			reminderLinearLayout.removeAllViews();
		}

	}

	/*
	 * onclick of the done button
	 */
	public void onClickDone(View v) {
		db.close();
		NavUtils.navigateUpFromSameTask(this);
	}

	/*
	 * updates the text of the pick a date button
	 */
	public void updateDateButtonText()
	{
		dateBtn.setText(MONTH + "/" + DAY + "/" + YEAR);
	}

	/*
	 * sets the text of the pick a date button to the current date
	 */
	public void resetDate()
	{
		YEAR = currentYear;
		MONTH = currentMonth;
		DAY = currentDay;
		updateDateButtonText();
	}

	/*
	 * (non-Javadoc)
	 * @see com.hornets.kplanner.fragments.DatePickerFragment.DatePickerDialogListener#onDateSelect(android.support.v4.app.DialogFragment, int, int, int)
	 * 
	 * called when the users closes the date picker dialog
	 * 
	 */
	@Override
	public void onDateSelect(DialogFragment dialog, int y, int monthOfYear, int dayOfMonth) {
		YEAR = y;
		MONTH = monthOfYear;
		DAY = dayOfMonth;
		updateDateButtonText();
	}

	/*
	 * removes all the values entered
	 */
	public void resetView() {
		//clear the data entered
		edittextName.setText("");
		edittextAmount.setText("");

		//reset the date
		resetDate();
		//turn the switch off and remove the reminder layout
		if(reminderSwitch.isChecked() == true){
			reminderLinearLayout.removeAllViews();
			reminderSwitch.setChecked(false);		
		}
	}

}
