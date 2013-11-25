package com.hornets.kplanner.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hornets.kplanner.R;
import com.hornets.kplanner.database.KPlannerReaderContract.KPlannerEntry;
import com.hornets.kplanner.database.KPlannerSQLHelper;
import com.hornets.kplanner.dataobjects.DbEntryConverter;
import com.hornets.kplanner.dataobjects.Income;
import com.hornets.kplanner.fragments.DatePickerFragment;
import com.hornets.kplanner.fragments.HourPickerFragment;
import com.hornets.kplanner.fragments.RatePickerFragment;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @author Rana Hayajneh
 * @author Mehmet Kologlu
 * @version November 24 2013
 * 
 * Class for the Income Activity of KPlanner
 *
 */

public class IncomeActivity extends FragmentActivity implements
		DatePickerFragment.DatePickerDialogListener,
		HourPickerFragment.IHourPickerListener,
		RatePickerFragment.IRatePickerListener {
	private RadioButton onCampusRadioButton;
	private RadioButton offCampusRadioButton;
	private RadioButton otherIncomeButton;
	private TextView hourView;
	private TextView rateView;
	private Button dateBtn;
	private Spinner recurrenceSpinner;
	private int currentYear;
	private int currentMonth;
	private int currentDay;
	private Calendar c;
	private String type;
	public static int YEAR;
	public static int MONTH;
	public static int DAY;
	public DbEntryConverter converter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);
		// Show the Up button in the action bar.
		setupActionBar();
		loadViews();
		converter = new DbEntryConverter(getApplicationContext());
		// get a calendar
		initializeCalendar();
		checkEdit();
	}

	/**
	 * Checks to see if the user is viewing the income page in Edit mode
	 */
	private void checkEdit() {
		try {

			Income income = null;
			if (EditActivity.editOffCampus) {
				EditActivity.editOffCampus = false;
				offCampusRadioButton.setChecked(true);
				income = converter.getOnCampusIncome();
				hourView.setText("" + income.getHours());
				rateView.setText("" + income.getRate());
				dateBtn.setText(getDate(income.getpayDate()));

				@SuppressWarnings("unchecked")
				ArrayAdapter<String> myAdap = (ArrayAdapter<String>) recurrenceSpinner
						.getAdapter(); // cast to an ArrayAdapter
				int spinnerPosition = myAdap
						.getPosition(income.getRecurrence());
				recurrenceSpinner.setSelection(spinnerPosition);

			} else if (EditActivity.editOnCampus) {
				EditActivity.editOnCampus = false;
				onCampusRadioButton.setChecked(true);
				income = converter.getOnCampusIncome();
				hourView.setText("" + income.getHours());
				rateView.setText("" + income.getRate());
				dateBtn.setText(getDate(income.getpayDate()));

				@SuppressWarnings("unchecked")
				ArrayAdapter<String> myAdap = (ArrayAdapter<String>) recurrenceSpinner
						.getAdapter(); // cast to an ArrayAdapter
				int spinnerPosition = myAdap
						.getPosition(income.getRecurrence());
				recurrenceSpinner.setSelection(spinnerPosition);
			} else if (EditActivity.editOther) {
				EditActivity.editOther = false;
				otherIncomeButton.setChecked(true);
				income = converter.getOtherIncome();
				hourView.setText("" + income.getHours());
				rateView.setText("" + income.getRate());
				dateBtn.setText(getDate(income.getpayDate()));

				@SuppressWarnings("unchecked")
				ArrayAdapter<String> myAdap = (ArrayAdapter<String>) recurrenceSpinner
						.getAdapter(); // cast to an ArrayAdapter
				int spinnerPosition = myAdap
						.getPosition(income.getRecurrence());
				recurrenceSpinner.setSelection(spinnerPosition);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Converts the date in milliseconds to date in dd/MM/yyyy format
	 * 
	 * @param paydate
	 *            the originally entered date in milliseconds
	 * @return the originally entered date in dd/MM/yyyy format
	 */
	private String getDate(long getpayDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
				Locale.ENGLISH);
		String dateString = formatter.format(new Date(getpayDate));
		return dateString;
	}

	/**
	 * Initializes the calendar
	 */
	private void initializeCalendar() {
		c = Calendar.getInstance();
		currentYear = c.get(Calendar.YEAR);
		currentMonth = c.get(Calendar.MONTH) + 1; // +1 to compensate for 0
													// indexing
		currentDay = c.get(Calendar.DAY_OF_MONTH);
		resetDate();

		// update the date picker button to display the date which is the
		// current date
		updateDateButtonText();
	}

	/**
	 * loads the views on the page
	 */
	private void loadViews() {
		onCampusRadioButton = (RadioButton) findViewById(R.id.onCampusIncomeButton);
		offCampusRadioButton = (RadioButton) findViewById(R.id.offCampusIncomeButton);
		otherIncomeButton = (RadioButton) findViewById(R.id.otherIncomeButton);
		hourView = (TextView) findViewById(R.id.incomeHoursView);
		rateView = (TextView) findViewById(R.id.ratesView);
		dateBtn = (Button) findViewById(R.id.income_button_datepicker);
		recurrenceSpinner = (Spinner) findViewById(R.id.incomeSpinner);

	}

	/**
	 * updates the date on the date button
	 */
	private void updateDateButtonText() {
		dateBtn.setText(MONTH + "/" + DAY + "/" + YEAR);

	}

	/**
	 * Resets the date on the date button
	 */
	private void resetDate() {
		YEAR = currentYear;
		MONTH = currentMonth;
		DAY = currentDay;
		updateDateButtonText();
	}

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

	/**
	 * Checks the on campus radio button and unchecks the previously checked one
	 * 
	 * @param view
	 */
	public void onCampus(View view) {
		offCampusRadioButton.setChecked(false);
		otherIncomeButton.setChecked(false);
		type = "ON";
	}

	/**
	 * Checks the off campus radio button and unchecks the previously checked
	 * one
	 * 
	 * @param view
	 */
	public void offCampus(View view) {
		onCampusRadioButton.setChecked(false);
		otherIncomeButton.setChecked(false);
		type = "OFF";
	}

	/**
	 * Checks the other income radio button and unchecks the previously checked
	 * one
	 * 
	 * @param view
	 */
	public void otherIncome(View view) {
		onCampusRadioButton.setChecked(false);
		offCampusRadioButton.setChecked(false);
		type = "OTHER";
	}

	/**
	 * Display the Hour Picker Dialog
	 * 
	 * @param view
	 */
	public void onHourClicked(View view) {
		HourPickerFragment hourPicker = new HourPickerFragment();
		hourPicker.show(getSupportFragmentManager(), "hourpicker");
	}

	/**
	 * Displays the Rate Picker when the user clicks the rate button
	 * 
	 * @param view
	 */
	public void enterRate(View view) {
		RatePickerFragment ratePicker = new RatePickerFragment();
		ratePicker.show(getSupportFragmentManager(), "rate_dialog");

	}

	@Override
	public void onHourSet(int value) {
		hourView.setText("" + value);
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

	}

	/**
	 * Displays the Date Picker dialog when the user click on the date button
	 * 
	 * @param view
	 */
	public void showDatePickerDialog(View v) {
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.show(getSupportFragmentManager(), "datePicker");
	}

	/**
	 * Takes the user back to the home page
	 * 
	 * @param view
	 */
	public void done(View view) {
		Intent goHome = new Intent(this, HomeActivity.class);
		startActivity(goHome);

	}

	/**
	 * Saves the information entered by the user
	 * 
	 * @param view
	 */
	@SuppressLint("ShowToast")
	public void onSave(View view) {

		if (!(onCampusRadioButton.isChecked()
				|| offCampusRadioButton.isChecked() || otherIncomeButton
					.isChecked())) {
			Toast.makeText(getApplicationContext(),
					"Please check an income type!", 4).show();
		} else if (rateView.getText().toString().equals("0.00")) {
			Toast.makeText(getApplicationContext(), "Please enter rate!", 4)
					.show();
		} else if (recurrenceSpinner.getSelectedItem().equals("Recurrence")) {
			Toast.makeText(getApplicationContext(),
					"Please select recurrence!", 4).show();
		} else {
			updateDB(type);
			Toast.makeText(getApplicationContext(), "Saved!", 2).show();
		}
	}

	/**
	 * Updates the income table in the database with the income data.
	 * 
	 * @param type
	 *            this represents the type of income
	 */
	public void updateDB(String type) {
		// retrieve the values
		String hours = hourView.getText().toString();
		String rate = rateView.getText().toString();
		String input = MONTH + " " + DAY + " " + YEAR;

		String recurrence = recurrenceSpinner.getSelectedItem().toString();

		Date enterDate;
		String date = "";
		try {
			enterDate = new SimpleDateFormat("MM dd yyyy", Locale.ENGLISH)
					.parse(input);
			date = enterDate.getTime() + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		KPlannerSQLHelper dbHelper = new KPlannerSQLHelper(
				getApplicationContext(), type, null, 0);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// remove previous entries
		if (KPlannerSQLHelper.INITIALIZED) {
			db.execSQL("DELETE FROM " + KPlannerEntry.INCOME_TABLE_NAME
					+ " WHERE " + KPlannerEntry.INCOME_COLUMN_TYPE + " = '"
					+ type + "';");
		}

		// map of values
		ContentValues values = new ContentValues();
		values.put(KPlannerEntry.INCOME_COLUMN_TYPE, type);
		values.put(KPlannerEntry.INCOME_COLUMN_RATE, rate);
		values.put(KPlannerEntry.INCOME_COLUMN_HOUR, hours);
		values.put(KPlannerEntry.INCOME_COLUMN_DATE, date);
		values.put(KPlannerEntry.INCOME_COLUMN_RECURRENCE, recurrence);

		// Insert the new row
		@SuppressWarnings("unused")
		long newRowId;
		newRowId = db.insert(KPlannerEntry.INCOME_TABLE_NAME, null, values);
		KPlannerSQLHelper.INITIALIZED = true;
	}

	@Override
	public void onDateSelect(DialogFragment dialog, int year, int monthOfYear,
			int dayOfMonth) {
		YEAR = year;
		MONTH = monthOfYear;
		DAY = dayOfMonth;
		updateDateButtonText();
	}

}
