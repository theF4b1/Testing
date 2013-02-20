package com.example.testing;

import java.util.ArrayList;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.testing.MESSAGE";
	
	//Adapter und ArrayList für die ListView
	ArrayAdapter<String> adapter;
	ArrayList<String> listItems=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Einfach übernehmen als Adapter-Konstruktor.
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		ListView listView = (ListView) findViewById(R.id.listView1);
		String message = editText.getText().toString();
		//listItems wird die Nachricht hinzugefügt
		listItems.add(message);
		//Der Adapter benachrichtigt die ListView über eine Änderung der Daten
		adapter.notifyDataSetChanged();
		//Liste zum ende scrollen:
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
		
		
		try {
	        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
	        r.play();
	    } catch (Exception e) {}
	}

}
