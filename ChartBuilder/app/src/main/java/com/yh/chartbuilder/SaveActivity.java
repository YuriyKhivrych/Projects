package com.yh.chartbuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SaveActivity extends Activity {

	public final static String MAP_TYPE = "MAP";
	public final static String BITMAP_TYPE = "BITMAP";
	
	private EditText edName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_layout);
		
		edName = (EditText) findViewById(R.id.save_editText);
		
	}
	
	public void saveAsMap(View v){
		if(edName.getText().toString() == ""){
			Toast.makeText(this, "Input name", Toast.LENGTH_SHORT).show();
			return;
		}
		else{
			Intent intent = new Intent();
			intent.setType(MAP_TYPE);
		    //intent.putExtra("type", MAP_TYPE);
		    intent.putExtra("name", edName.getText().toString());
		    setResult(RESULT_OK, intent);
		    finish();
		}
		
	}
	
	public void saveAsBitmap(View v){
		if(edName.getText().toString() == ""){
			Toast.makeText(this, "Input name", Toast.LENGTH_SHORT).show();
			return;
		}
		else{
			Intent intent = new Intent();
			intent.setType(BITMAP_TYPE);
			//intent.putExtra("type", BITMAP_TYPE);
		    intent.putExtra("name", edName.getText().toString());
		    setResult(RESULT_OK, intent);
		    finish();
		}
		
	}
	
}
