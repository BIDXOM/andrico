/****************************************
 * 		                       			*
 *     Copyright 2009 Andrico Team 		*
 *   http://code.google.com/p/andrico/	*
 *										*
 ****************************************/

package org.andrico.andrico;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.gdata.data.Feed;
import com.google.gdata.util.ServiceException;




public class SettingsActivity extends Activity 
{
	final static String TAG = "Settings";
	private SharedPreferences SharedPreferences;
	
	
		@Override
	    public void onCreate(Bundle savedInstanceState)
		{
	        super.onCreate(savedInstanceState);
	        Window  w = getWindow(); 
	        w.requestFeature(Window.FEATURE_LEFT_ICON);   
	        setContentView(R.layout.settings);
	        w.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_andrico);
	        Intent i = this.getIntent();
	        
	        CheckBox delBox = (CheckBox)this.findViewById(R.id.DeleteContacts);
	        CheckBox picBox = (CheckBox)this.findViewById(R.id.DownloadPic);
	        
	        SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);  
	        
	        if (SharedPreferences.getString(Preferences.DELETE_CONTACTS, "no") == "no")
	        {
	        	delBox.setChecked(true);
	        }
	        else
	        {
	        	delBox.setChecked(false);
	        }
	        
	        delBox.setOnCheckedChangeListener(new OnCheckedChangeListener()
	        {	    
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) 
				{
					SharedPreferences.Editor editor = SharedPreferences.edit();
					if (SharedPreferences.getString(Preferences.DELETE_CONTACTS, "no") == "no")
					{
						editor.putString(Preferences.DELETE_CONTACTS, "yes");
					}
					else
					{
						editor.putString(Preferences.DELETE_CONTACTS, "no");
					}
					
					editor.commit();
				}
			}); 
	        
	        
	        picBox.setOnCheckedChangeListener(new OnCheckedChangeListener()
	        {	    
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) 
				{
					
				}
			});

	        this.findViewById(R.id.BackToMenu).setOnClickListener(new OnClickListener()
	        {
				public void onClick(View v)
				{
					Intent i = new Intent(SettingsActivity.this,MainActivity.class);
	                startActivity(i);
	                finish();
	       		}
			});
	    }
}