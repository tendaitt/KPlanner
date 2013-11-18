package com.hornets.kplanner.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.hornets.kplanner.R;

/**
 * 
 * @author Rana Hayajneh
 * @version Oct 30, 2013
 * 
 *          EditActivity allows the user to access their saved data and change
 *          it or delete it.
 * 
 */

public class EditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		/*
		 * final TabWidget tabWidge = tabHost.getTabWidget(); final FrameLayout
		 * tabContent = tabHost.getTabContentView();
		 */

		final TabHost tabHost = (TabHost) findViewById(R.id.tabhost);

		tabHost.setup();
		TabSpec spec1 = tabHost.newTabSpec("Tab1");
		spec1.setContent(R.id.Income);
		spec1.setIndicator("Tab1", null);

		TabSpec spec2 = tabHost.newTabSpec("Tab2");
		spec2.setContent(R.id.Expenses);
		spec2.setIndicator("Tab2", null);

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
			TextView textView = (TextView) tabhost.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	public void Income(View view) {
		Intent intent = new Intent(this, IncomeActivity.class);
		startActivity(intent);
	}

	public void Expenses(View view) {
		Intent intent = new Intent(this, ExpensesActivity.class);
		startActivity(intent);
	}
	
	//view all entries 
	
}
