package com.hornets.kplanner.activities;


import com.hornets.kplanner.R;
import com.hornets.kplanner.dataobjects.DbEntryConverter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

/** 
 * 
 * @author Rana Hayajneh
 * @version Nov 14, 2013
 *
 */
public class SummaryActivity extends Activity {

	private TextView onCampusView;
	private TextView offCampusView;
	private TextView otherIncomeView;
	private DbEntryConverter converter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		
		onCampusView = (TextView) findViewById(R.id.onCampus);
		offCampusView = (TextView) findViewById(R.id.offCampus);
		otherIncomeView = (TextView) findViewById(R.id.others);
		
		loadIncomeValues();
		
	}

	private void loadIncomeValues() {
		converter = new DbEntryConverter(getApplicationContext());
		
		//DEAL WITH NULL RETURNS
		String onCampus = converter.getOnCampusIncome().toString();
		String offCampus = converter.getOffCampusIncome().toString();
		String otherIncome = converter.getOtherIncome().toString();
		
		onCampusView.setText(onCampus);
		offCampusView.setText(offCampus);
		otherIncomeView.setText(otherIncome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
	

/*public void viewdb(View v) {
	((TextView) findViewById(R.id.view)).setTextSize(15);
	((TextView) findViewById(R.id.view)).setText(" " + this.printDb());
}*/
}
