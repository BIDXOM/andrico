/****************************************
 * 		                       			*
 *     Copyright 2009 Andrico Team 		*
 *   http://code.google.com/p/andrico/	*
 *										*
 ****************************************/
package org.andrico.andrico;


import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import org.andrico.andrico.content.Contact;
import org.andrico.andrico.content.DBContact;
import org.andrico.andrico.facebook.LoginActivity;

import com.google.gdata.data.Feed;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import 	android.widget.ExpandableListView;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView.OnChildClickListener;



public class ContactList extends ListActivity
{
	private LinkedList<Contact> contacts = null;
	final static String TAG = "ContactList";
	private static int CONFIG_ORDER = 0;
	private SimpleAdapter listAdapter = null;
	protected ListView list;
	TextView selection;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    Window  w = getWindow(); 
	    w.requestFeature(Window.FEATURE_LEFT_ICON);   
	    setContentView(R.layout.contacts);
	    w.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_andrico);
	    
	    DBContact db = new DBContact();
     	
	    contacts = db.getContactList(ContactList.this);

	    LinkedList<Map<String, String>> conts = new LinkedList<Map<String, String>>();
	    
	    if (contacts != null)
		{
	    	for(int j = 0; j < contacts.size(); j++) 
         	{
        		TreeMap<String, String> cont = new TreeMap<String, String> ();
                
        		cont.put("contact", contacts.get(j).getName() + " " + contacts.get(j).getSecondName());
                cont.put("fbid", contacts.get(j).getFBid());
        		conts.add(cont);
            }
	    	listAdapter = new SimpleAdapter(this, conts, R.layout.group_row, 
         					new String[] {"contact", "fbid"},
         					new int[] {R.id.NameOfGroup, R.id.FBID});
         	
         	/*listAdapter = new SimpleAdapter(this, conts, android.R.layout.simple_list_item_2, 
                    new String[] {"contact", "fbid"},
                    new int[] {android.R.id.text1, android.R.id.text2});*/
        }  
	    
	  //getListView().setItemsCanFocus(true);
    	
	    
	    this.list = (ListView) this.findViewById(android.R.id.list);
    	list.setAdapter(listAdapter);
    	getListView().setTextFilterEnabled(true);
    	//getListView().setClickable(true);
    	//getListView().setFocusable(true);
    	
    	/*list.setOnItemClickListener(new OnItemClickListener()
    	{
			public void onItemClick(AdapterView parent, View v, int position, long id) 
			{
				Toast.makeText(ContactList.this, "CLICKED", Toast.LENGTH_SHORT);
			}
    		
    	});*/
    	
    	    	
    	this.findViewById(R.id.list_empty).setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{        		
				Intent i = new Intent(ContactList.this, Synchronize.class);
				String[] s = {"",""};
				i.putExtra("ConfigOrder", CONFIG_ORDER);
				i.putExtra("PostTitleAndContent", s);
				startActivity(i);
	            finish();
       		}
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		
		AlertDialog dialog = new AlertDialog.Builder(ContactList.this)
										.setTitle("CLICKED")
										.setMessage("contact number " + Integer.toString(position))
										.setPositiveButton("OK", 
										new DialogInterface.OnClickListener() 
										{
											public void onClick(DialogInterface dialog, int whichButton)
											{
												dialog.dismiss();
											}
										}).create();
		dialog.show();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
    { 
    	if(keyCode==KeyEvent.KEYCODE_BACK)
    	{
    		Intent i = new Intent(ContactList.this,MainActivity.class);
    		i.putExtra("ConfigOrder", CONFIG_ORDER);
    		startActivity(i);
            finish();
            return true;
    	}
		return false; 
	}
	
 }


