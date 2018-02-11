package com.moshekaplan.android.wifiautologin.activities;



import com.libel.android.wifiautologin.AboutActivity;
import com.libel.android.wifiautologin.AjudaActivity;
import com.moshekaplan.android.EasyHTTPClient;
import com.moshekaplan.android.wifiautologin.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class EditConfiguration extends ActionBarActivity

{
	private EditText usernameField;
	private EditText passwordField;
	private AdView mAdView;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_configuration);
		usernameField = (EditText) findViewById(R.id.username);
		passwordField = (EditText) findViewById(R.id.password);
		loadStoredUsernamePassword();
		mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

	}

	private void loadStoredUsernamePassword(){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String storedUsername = preferences.getString("username", "");
		String storedPassword = preferences.getString("password", "");
		
		//EditText usernameField = (EditText) findViewById(R.id.username);
		//EditText passwordField = (EditText) findViewById(R.id.password);
		
		usernameField.setText(storedUsername);
		passwordField.setText(storedPassword);
	}
	

	public void manual_logon(View v) {
    	
    	//EditText usernameField = (EditText) findViewById(R.id.username);
		//EditText passwordField = (EditText) findViewById(R.id.password);
		
		final String username = usernameField.getText().toString();
		final String password = passwordField.getText().toString();
		
		Log.d("WifiConnectedReceiver", "username: " + username + " password: " + password);
		new Thread(new Runnable() {
			@Override
			public void run() {
				EasyHTTPClient.connect(username, password);
				}
		}).start();
		
		Toast.makeText(this, R.string.sent, Toast.LENGTH_SHORT).show();

	}
	
	
	public void onSave(View v) {
		//EditText usernameField = (EditText) findViewById(R.id.username);
		//EditText passwordField = (EditText) findViewById(R.id.password);
		
		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();
		
		Editor preferencesEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
		preferencesEditor.putString("username", username);
		preferencesEditor.putString("password", password);
		
		preferencesEditor.commit();
		Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.about_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.about:
	            displayAbout();
	            return true;
            case R.id.ajuda:{
                displayAjuda();
            }
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	private void displayAbout() {
		Intent intent = new Intent(this, AboutActivity.class);
		startActivity(intent);
		finish();
	}

	private void displayAjuda() {
		Intent intent = new Intent(this, AjudaActivity.class);
		startActivity(intent);
		finish();
	}

}

