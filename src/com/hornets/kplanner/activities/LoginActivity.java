package com.hornets.kplanner.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.hornets.kplanner.R;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version 20 October 2013
 *
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void setUpNewUser(View view){
		Intent newUser = new Intent(this,NewUserActivity.class);
		startActivity(newUser);
	}
	
	public void dummyToHome(View view){
		Intent goHome = new Intent(this, HomeActivity.class);
		startActivity(goHome);
	}

}
