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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gdata.data.Feed;
import com.google.gdata.util.ServiceException;




public class MainActivity extends Activity 
{
    /** Called when the activity is first created. */
    
	Context cont;
	
	final static String TAG = "MainActivity";
	public static Feed resultFeed = null;
	int viewStatus = 0;
	
	
		@Override
	    public void onCreate(Bundle savedInstanceState)
		{
	        super.onCreate(savedInstanceState);
	        Window  w = getWindow(); 
	        w.requestFeature(Window.FEATURE_LEFT_ICON);   
	        setContentView(R.layout.main);
	        w.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_andrico);
	        
	        Intent i = this.getIntent();
	        
	        
	        this.findViewById(R.id.ViewContacts).setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
				{        		
					Intent i = new Intent(MainActivity.this, ContactList.class);
					String[] s = {"",""};
					i.putExtra("PostTitleAndContent", s);
					startActivity(i);
		            finish();
	       		}
			}); 
	        
	        this.findViewById(R.id.Synchronize).setOnClickListener(new OnClickListener()
	        {
				public void onClick(View v)
				{   
					Intent i = new Intent(MainActivity.this, Synchronize.class);
					startActivity(i);
					finish();
	       		}
			});
	        
	        this.findViewById(R.id.Settings).setOnClickListener(new OnClickListener()
	        {
				public void onClick(View v)
				{
					Intent i = new Intent(MainActivity.this, SettingsActivity.class);
					startActivity(i);
					finish();
	       		}
			});
	        
	        this.findViewById(R.id.Exit).setOnClickListener(new OnClickListener(){
				public void onClick(View v)
				{   
	                finish();
	       		}
			});
	    }
}