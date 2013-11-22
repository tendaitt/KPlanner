package com.hornets.kplanner.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.hornets.kplanner.R;
import com.hornets.kplanner.database.KPlannerSQLHelper;
import com.hornets.kplanner.database.KPlannerReaderContract.KPlannerEntry;
import com.hornets.kplanner.fragments.DatePickerFragment;

import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.Toast;

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

	private Button dateBtn;
	private LinearLayout reminderLinearLayout;
	private Switch reminderSwitch;
	private EditText edittextName;
	private EditText edittextAmount;
	private Spinner spinnerType;

	private SQLiteDatabase db;
	private KPlannerSQLHelper dbHelper;

	private Calendar c;

	private Bundle extras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);

		populateLayout();
		initializeAndOpenDatabase();
	}

	/**
	 * initializes the layout, gets the intent, initializes the calendar, and populates the 
	 */
	private void populateLayout() {
		initializeLayoutElements();
		setDate();
		if(extras != null) {
			populateForEdit();
		}
	}

	/**
	 * initializes the instances variables as their respective the layout elements
	 */
	private void initializeLayoutElements() {
		//initialize layout elements
		dateBtn = (Button) findViewById(R.id.expenses_button_datepicker);
		reminderSwitch = (Switch) findViewById(R.id.expenses_switch_reminder);
		edittextName = (EditText) findViewById(R.id.expenses_edit_name);
		edittextAmount = (EditText) findViewById(R.id.expenses_edit_amount);
		spinnerType = (Spinner) findViewById(R.id.expenses_spinner_type);
		reminderLinearLayout = (LinearLayout) findViewById(R.id.expneses_linearayout_reminderentry);
		//get intent
		extras= getIntent().getExtras();
	}

	/**
	 * populates the name, type, and amount fields from the values as passed by the intent
	 */
	private void populateForEdit() {
		//make the name field display the name
		edittextName.setText(extras.getString("name"));
		//make the spinner display the type
		ArrayList<String> typesArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.expense_type_array)));
		spinnerType.setSelection(typesArray.indexOf(extras.getString("type")));
		//make the amount field display the amount
		edittextAmount.setText(extras.getString("amount"));
		//make the date field display the date
		dateBtn.setText(extras.getString("date"));
	}

	/**
	 * get the current date from the java calendar
	 */
	private void setDate() {
		c = Calendar.getInstance();
		YEAR = c.get(Calendar.YEAR);
		MONTH = c.get(Calendar.MONTH)+1; //+1 to compensate for 0 indexing
		DAY = c.get(Calendar.DAY_OF_MONTH);
		updateDateButtonText();
	}

	/**
	 * updates the text of the pick a date button as
	 */
	public void updateDateButtonText()
	{
		dateBtn.setText(MONTH + "/" + DAY + "/" + YEAR);
	}

	/**
	 * initializes a db helper and gets a writable database 
	 */
	private void initializeAndOpenDatabase() {
		dbHelper = new KPlannerSQLHelper(getApplicationContext(), "EXPENSES", null, 0);
		db = dbHelper.getWritableDatabase();
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

	/**
	 * onclick of the pick a date button, 
	 * displays the datepicker fragment
	 */
	public void showDatePickerDialog(View v){
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.show(getSupportFragmentManager(), "datePicker");
	}

	/**
	 * (non-Javadoc)
	 * @see com.hornets.kplanner.fragments.DatePickerFragment.DatePickerDialogListener#onDateSelect(android.support.v4.app.DialogFragment, int, int, int)
	 * 
	 * called when the users closes the date picker dialog
	 * displays the date selected as the text of the date button
	 * 
	 */
	@Override
	public void onDateSelect(DialogFragment dialog, int y, int monthOfYear, int dayOfMonth) {
		YEAR = y;
		MONTH = monthOfYear;
		DAY = dayOfMonth;
		updateDateButtonText();
	}

	/**
	 * onclick of the add button
	 */
	public void onClickAdd(View v) {

		//Retrive the values
		String name = edittextName.getText().toString();
		String type = spinnerType.getSelectedItem().toString();
		String amount = edittextAmount.getText().toString();
		String date = Integer.toString(DAY) + "/" + 
				Integer.toString(MONTH) + "/" +
				Integer.toString(YEAR);

		if(name.isEmpty())
		{
			Toast.makeText(getApplicationContext(), 
					"Please enter a name.", Toast.LENGTH_SHORT).show();
		}
		else if(type.equals("Select type"))
		{
			Toast.makeText(getApplicationContext(), 
					"Please select a type.", Toast.LENGTH_SHORT).show();
		}
		else if(amount.isEmpty())
		{
			Toast.makeText(getApplicationContext(), 
					"Please enter an amount.", Toast.LENGTH_SHORT).show();
		}
		else //all input fields are filled
		{
			if(extras != null){
				editInDatabase(name, type, amount, date);
			}
			else
				addToDatabase(name, type, amount, date);

			//DO SOMETHING WITH THE REMINDER

			//notify user
			Toast.makeText(getApplicationContext(), "Expense added", Toast.LENGTH_SHORT).show();

			//Reset view
			resetView();
		}
	}

	/**
	 * removes the entry corresponding to the editIntent's values,
	 * and adds one with the now selected data
	 * 
	 * @param name
	 * @param type
	 * @param amount
	 * @param date
	 */
	private void editInDatabase(String name, String type, String amount, String date) {
		dbHelper.delete(db, extras.getString("name"), extras.getString("type"), extras.getString("date"), extras.getString("amount"));
		addToDatabase(name, type, amount, date);
	}

	private void addToDatabase(String name, String type, String amount, String date) {
		// TODO Auto-generated method stub
		//map of values
		ContentValues values = new ContentValues();
		values.put(KPlannerEntry.EXPENSE_COLUMN_NAME, name);
		values.put(KPlannerEntry.EXPENSE_COLUMN_TYPE, type);
		values.put(KPlannerEntry.EXPENSE_COLUMN_DATE, date);
		values.put(KPlannerEntry.EXPENSE_COLUMN_AMOUNT, amount);

		//Insert the new row
		db.insert(KPlannerEntry.EXPENSE_TABLE_NAME, null, values);

	}

	/*
	 * onclick of the reminders switch
	 */
	public void onSwitchClicked(View v) {

		if(reminderSwitch.isChecked()){ //switch is turned on
			populateReminderLayout();
		}
		else //switch is turned off
		{
			reminderLinearLayout.removeAllViews();
		}
	}

	/**
	 * populates the reminder layout with views to be used to set up a reminder
	 */
	private void populateReminderLayout() {
		// TODO Auto-generated method stub
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

	/**
	 * onclick of the done button
	 * closes the database and goes to the homepage
	 */
	public void onClickDone(View v) {
		db.close();
		NavUtils.navigateUpFromSameTask(this);
	}

	/**
	 * removes all the values entered, and resets the data entered to the view
	 */
	public void resetView() {
		//clear the data entered
		edittextName.setText("");
		edittextAmount.setText("");
		spinnerType.setSelection(0);

		//reset the date
		setDate();
		//turn the switch off and remove the reminder layout
		if(reminderSwitch.isChecked() == true){
			reminderLinearLayout.removeAllViews();
			reminderSwitch.setChecked(false);		
		}
	}

}