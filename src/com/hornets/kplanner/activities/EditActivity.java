package com.hornets.kplanner.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
	private ListView expenseListView;
	private AdapterView.OnItemClickListener mMessageClickedHandler;

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
		expenseListView = (ListView) findViewById(R.id.view_list_expense);


		//instantiate the converter
		converter = new DbEntryConverter(getApplicationContext());

		tabHost.setup();
		switchTabs(tabHost);

		loadIncomeTextView();
		loadExpensesList();

	}

	/**
	 * loads the expenses in the database to the expense tab as a list view
	 * with onclick's taking the user to the edit page for that entry
	 */
	private void loadExpensesList() {
		//get the expenses array
		expenseArray = converter.getExpenseList();
		if(! (expenseArray.isEmpty()))
		{

			//make adapter
			ArrayAdapter<Expense> arrayAdapter = new ArrayAdapter<Expense>(this, 
					android.R.layout.simple_list_item_1, expenseArray);

			//set adapter
			expenseListView.setAdapter(arrayAdapter);

			//create the onclick listener
			mMessageClickedHandler = new OnItemClickListener() {
				public void onItemClick(AdapterView parent, View v, int position, long id) {
					Intent expenseIntent = new Intent(getApplicationContext(), ExpensesActivity.class);
					expenseIntent.putExtra("name", expenseArray.get(position).getName());
					expenseIntent.putExtra("type", expenseArray.get(position).getType());
					expenseIntent.putExtra("date", expenseArray.get(position).getDate());
					expenseIntent.putExtra("amount", expenseArray.get(position).getAmount());
					startActivity(expenseIntent);
				}
			};
			//set the onclick listener
			expenseListView.setOnItemClickListener(mMessageClickedHandler);
		}
		else //if no expense are entered
		{
			TextView emptyListText = new TextView(getApplicationContext());
			emptyListText.setText("You have no expense entered.");
		}
	}

	private void loadIncomeTextView(){ 
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
		spec2.setContent(R.id.view_relative_expense);
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