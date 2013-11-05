package com.hornets.kplanner.activities;

/*
 * Author: Rana Hayajneh
 * 
 * Version: Oct 30, 2013
 * 
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.hornets.kplanner.R;

public class EditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		/*final TabWidget tabWidge = tabHost.getTabWidget();
		final FrameLayout tabContent = tabHost.getTabContentView();*/
		
		final TabHost tabHost=(TabHost)findViewById(R.id.tabhost); 

		tabHost.setup();  
		TabSpec spec1=tabHost.newTabSpec("Tab1"); 
		spec1.setContent(R.id.Income);
		spec1.setIndicator("Tab1",null);  
		 
		TabSpec spec2=tabHost.newTabSpec("Tab2"); 
		spec2.setContent(R.id.Expenses); 
		spec2.setIndicator("Tab2",null);   
		
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() 

		{
			@Override
			public void onTabChanged(String arg0)
			{ 
				setTabColor(tabHost); 
			} 
		});
		setTabColor(tabHost);
	} 
	public void setTabColor(TabHost tabhost) {
		for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
		{ TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
		   
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
