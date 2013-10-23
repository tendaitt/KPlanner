package com.hornets.kplanner;

import com.hornets.kplanner.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class PlannerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.planner, menu);
        return true;
    }
    
	/*
	 * method for mehmet's test button to call for test purposes
	 */
	public void testMehmet(View view)
	{
		Intent intent = new Intent(this, ExpensesActivity.class);
		startActivity(intent);
	}
    
}