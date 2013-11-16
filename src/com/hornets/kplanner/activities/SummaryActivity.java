package com.hornets.kplanner.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.hornets.kplanner.R;

public class SummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
	
	/*public String printDb()
	{
	KPlannerSQLHelper dbHelper = new KPlannerSQLHelper(this);
	SQLiteDatabase dbase = dbHelper.getReadableDatabase();
	
	String[] projection = {
	KPlannerEntry.COLUMN_USER_NAME,
	KPlannerEntry.COLUMN_PASSWORD
	//KPlannerEntry.Column_saved_data
	};
 
	//how to sort results
	try{
		String sortedOrder="";
	}
	
	
	}
*/
}
