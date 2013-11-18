package com.hornets.kplanner.activities;


import java.util.ArrayList;

import com.hornets.kplanner.R;
import com.hornets.kplanner.dataobjects.DbEntryConverter;
import com.hornets.kplanner.dataobjects.Expense;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/** 
 * 
 * @author Rana Hayajneh
 * @author Mehmet Kologlu
 * @version Nov 18, 2013
 *
 */
public class SummaryActivity extends Activity {

	private TextView onCampusView;
	private TextView offCampusView;
	private TextView otherIncomeView;
	private DbEntryConverter converter;
	private ArrayList<Expense> expenseArray;
	private LinearLayout expenseLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		onCampusView = (TextView) findViewById(R.id.summary_text_onCampus);
		offCampusView = (TextView) findViewById(R.id.summary_text_offCampus);
		otherIncomeView = (TextView) findViewById(R.id.summary_text_others);
		expenseLinearLayout = (LinearLayout) findViewById(R.id.summary_linear_expenselist);

		converter = new DbEntryConverter(getApplicationContext());

		loadIncomeValues();
		loadExpenseValues();
	}

	private void loadIncomeValues() {

		//DEAL WITH NULL RETURNS
		String onCampus = converter.getOnCampusIncome().toString();
		String offCampus = converter.getOffCampusIncome().toString();
		String otherIncome = converter.getOtherIncome().toString();

		onCampusView.setText(onCampus);
		offCampusView.setText(offCampus);
		otherIncomeView.setText(otherIncome);
	}

	private void loadExpenseValues() {
		expenseArray = converter.getExpenseList();
		
		int i=0;
		
		for(Expense e: expenseArray)
		{
			//a row
			LinearLayout row = new LinearLayout(getApplicationContext());
			row.setOrientation(0);
			
			//the name of the expense
			TextView name = new TextView(getApplicationContext());
			name.setText(e.getName());
			TextView type = new TextView(getApplicationContext());
			type.setText(e.getType());
			TextView date = new TextView(getApplicationContext());
			type.setText(e.getDate());
			TextView amount = new TextView(getApplicationContext());
			type.setText(e.getAmount());
			
			row.addView(name);
			row.addView(type);
			row.addView(date);
			row.addView(amount);
			
			i++;
			
			expenseLinearLayout.addView(row);			
		}
		Toast.makeText(getApplicationContext(),Integer.toString(i),Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
}
