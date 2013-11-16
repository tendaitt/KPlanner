package com.hornets.kplanner.activities;


import com.hornets.kplanner.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/** 
 * 
 * @author Rana Hayajneh
 * @version Nov 14, 2013
 *
 */
public class SummaryActivity extends Activity {
	//obj var = new obj();

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
	KPlannerEntry.COLUMN_INCOME,
	KPlannerEntry.COLUMN_EXPENSES
	//KPlannerEntry.Column_saved_data
	};
 
	//how to sort results
	// sorting data
	try {
		String sortOrder = KPlannerSQLHelper.COLUMN_NAME_CT + " DESC";

		Cursor c = dbase.query(KPlannerSQLHelper.TABLE_NAME, projection, null, null,
				null, null, sortOrder);

		StringBuilder sb = new StringBuilder();
		c.moveToFirst();
		for (String i : c.getColumnNames()) {
			sb.append(i);
			sb.append("|");
		}
		sb.append("\n");

		while (!c.isAfterLast()) {
			sb.append(c.getString(0));
			sb.append("|");
			for (int i = 1; i < 3; i++) {
				sb.append(c.getInt(i));
				sb.append("|");
			}
			sb.append(c.getString(3));
			sb.append("\n");
			c.moveToNext();
		}
		return sb.toString();
	} catch (Exception e) {
		return "data is empty";
	}
}

public void viewdb(View v) {
	((TextView) findViewById(R.id.view)).setTextSize(15);
	((TextView) findViewById(R.id.view)).setText(" " + this.printDb());
}
*/
}
