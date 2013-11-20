package com.hornets.kplanner.activities;


import java.util.ArrayList;

import com.hornets.kplanner.R;
import com.hornets.kplanner.dataobjects.DbEntryConverter;
import com.hornets.kplanner.dataobjects.Expense;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

/** 
 * 
 * @author Rana Hayajneh
 * @author Mehmet Kologlu
 * @version Nov 20, 2013
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

		try{
			converter = new DbEntryConverter(getApplicationContext());
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}

		loadIncomeValues();
		loadExpenseValues();
	}

	private void loadIncomeValues() {

		if(!(converter==null)){
			try{
				String onCampus = converter.getOnCampusIncome().toString();
				String offCampus = converter.getOffCampusIncome().toString();
				String otherIncome = converter.getOtherIncome().toString();
				onCampusView.setText(onCampus);
				offCampusView.setText(offCampus);
				otherIncomeView.setText(otherIncome);
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
		}
	}

	private void loadExpenseValues() {
		expenseArray = converter.getExpenseList();

		if(! (expenseArray.isEmpty()))
		{
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

				expenseLinearLayout.addView(row);		
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
}
