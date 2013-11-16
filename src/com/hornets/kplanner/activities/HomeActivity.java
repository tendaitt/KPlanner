package com.hornets.kplanner.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.hornets.kplanner.R;

/**
 * 
 * @author Rana Hayajneh
 * @version Oct 27, 2013
 * 
 *          This is HomeActivity, it contains all the methods to navigate from
 *          the home page to all different activities
 * 
 */

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	// Method to navigate to Expenses activity
	public void LoadExpenses(View view) {
		Intent intent = new Intent(this, ExpensesActivity.class);
		startActivity(intent);
	}

	// Method to navigate to Income activity
	public void LoadIncome(View view) {
		Intent intent = new Intent(this, IncomeActivity.class);
		startActivity(intent);
	}

	// Exit App method
	public void Exit(View view) {
		finish();
		System.exit(0);
	}

	// Method to navigate from home screen to Edit activity
	public void LoadEdit(View view) {
		Intent intent = new Intent(this, EditActivity.class);
		startActivity(intent);
	}

	// summary activity
	public void LoadSummary(View view) {
		Intent intent = new Intent(this, SummaryActivity.class);
		startActivity(intent);
	}

	// HowMuch Activity
	public void LoadHowMuch(View view) {
		Intent intent = new Intent(this, HowMuch.class);
		startActivity(intent);
	}
	//done
}