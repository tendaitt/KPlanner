package com.hornets.kplanner.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.hornets.kplanner.R;
import com.hornets.kplanner.dataobjects.DbEntryConverter;
import com.hornets.kplanner.dataobjects.Expense;

/**
 * 
 * @author Rana Hayajneh
 * @author Mehmet Kologlu
 * @version Nov 20, 2013
 * 
 *          EditActivity allows the user to access their saved data and change
 *          it or delete it.
 * 
 */

public class EditActivity extends Activity {

	private TextView onCampusView;
	private TextView offCampusView;
	private TextView otherIncomeView;
	private DbEntryConverter converter;
	private ArrayList<Expense> expenseArray;
	private LinearLayout expenseLinearLayout;

	public static boolean editOnCampus = false;
	public static boolean editOffCampus = false;
	public static boolean editOther = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		/*
		 * final TabWidget tabWidge = tabHost.getTabWidget(); final FrameLayout
		 * tabContent = tabHost.getTabContentView();
		 */

		final TabHost tabHost = (TabHost) findViewById(R.id.tabhost);

		//instantiate the view elements
		onCampusView = (TextView) findViewById(R.id.onCampus);
		offCampusView = (TextView) findViewById(R.id.offCampus);
		otherIncomeView = (TextView) findViewById(R.id.other);
		expenseLinearLayout = (LinearLayout) findViewById(R.id.view_linear_expense);

		//instantiate the converter
		converter = new DbEntryConverter(getApplicationContext());

		tabHost.setup();
		switchTabs(tabHost);

		loadIncomeTextView();
		loadExpensesList();

	}

	private void loadExpensesList() {
		//get the expenses array
		expenseArray = converter.getExpenseList();

		if(! (expenseArray.isEmpty())) //is there anything to display in the expense list
		{
			for(Expense e: expenseArray)
			{
				LinearLayout row = new LinearLayout(getApplicationContext());
				row.setOrientation(0);

				TextView summary = new TextView(getApplicationContext());
				summary.setText(e.getSummary());
				row.addView(summary);

				//				TextView name = new TextView(getApplicationContext());
				//				name.setText(e.getName());
				//				name.setPadding(5, 5, 5, 5);
				//				TextView type = new TextView(getApplicationContext());
				//				type.setText(e.getType());
				//				type.setPadding(5, 5, 5, 5);
				//				TextView date = new TextView(getApplicationContext());
				//				type.setText(e.getDate());
				//				date.setPadding(5, 5, 5, 5);
				//				TextView amount = new TextView(getApplicationContext());
				//				type.setText("$ " + e.getAmount());
				//
				//				row.addView(name);
				//				row.addView(type);
				//				row.addView(date);
				//				row.addView(amount);

				expenseLinearLayout.addView(row);		
			}
		}
		else //if no expense are entered
		{
			TextView emptyListText = new TextView(getApplicationContext());
			emptyListText.setText("You have no expense entered.");
			expenseLinearLayout.addView(emptyListText);
		}
	}

	private void loadIncomTextView(){ 
	try{
		
		String onCampus = converter.getOnCampusIncome().getSummary();
		String offCampus = converter.getOffCampusIncome().getSummary();
		String other = converter.getOtherIncome().getSummary();
		onCampusView.setText(onCampus);
		offCampusView.setText(offCampus);
		otherIncomeView.setText(other);
	}
	catch(NullPointerException e)
	{
		e.printStackTrace();
	}
}
	
	// create an on click for textviews, and pass in the parameters 
	public void editOnCampus(View view)
	{
		editOnCampus = true; 
		Intent intent = new Intent(this, IncomeActivity.class);
		startActivity(intent);
	}


	public void editOffCampus(View view)
	{
		editOffCampus = true;
		Intent intent = new Intent(this, IncomeActivity.class);
		startActivity(intent);
	}

	public void editOther(View view)
	{
		editOther = true;
		Intent intent = new Intent(this, IncomeActivity.class);
		startActivity(intent);

	}


	private void switchTabs(final TabHost tabHost) {

		TabSpec spec1 = tabHost.newTabSpec("Tab1");
		spec1.setContent(R.id.Income);
		spec1.setIndicator("Income", null);

		TabSpec spec2 = tabHost.newTabSpec("Tab2");
		spec2.setContent(R.id.Expenses);
		spec2.setIndicator("Expenses", null);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);

		tabHost.setOnTabChangedListener(new OnTabChangeListener()

		{
			@Override
			public void onTabChanged(String arg0) {
				setTabColor(tabHost);
			}
		});
		setTabColor(tabHost);
	}

	public void setTabColor(TabHost tabhost) {
		for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
			tabhost.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}
}
