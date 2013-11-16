package com.hornets.kplanner.activities;

import com.hornets.kplanner.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HowMuch extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_how_much);
  
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.how_much, menu);
		return true;
	}

}
