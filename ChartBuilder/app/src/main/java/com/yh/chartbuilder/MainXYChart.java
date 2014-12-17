package com.yh.chartbuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.SortedMap;

import org.achartengine.GraphicalView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainXYChart extends Activity implements OnSharedPreferenceChangeListener{
	
	SharedPreferences sharedPrefs;

    //test
	//fields to get coordinates of points
	EditText xValue;
	EditText yValue;
	
	//requestCode for startActivityForResult(Intent, int requestCode)
	private final static int SAVE_ACTIVITY = 0;
	
	private static GraphicalView chartView;
	private LineGraph lineGraph;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		xValue = (EditText) findViewById(R.id.editXValue);
		yValue = (EditText) findViewById(R.id.editYValue);
		
		lineGraph = new LineGraph();
		
		// Getting a reference to LinearLayout of the MainActivity Layout
	  	LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);
	  	
	  	chartView = lineGraph.getView(this);
	  	
	 	// Adding the Line Chart to the LinearLayout
	  	chartContainer.addView(chartView);
	  	
	  	// get SharedPreferences, which works with settings file
	  	sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	    // full clear of prefs
	    sharedPrefs.edit().clear().commit();
	  	sharedPrefs.registerOnSharedPreferenceChangeListener(this);
	  	
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
	}

	

	@Override
	public void onSharedPreferenceChanged(
			SharedPreferences sharedPreferences, String key) {
				// TODO Auto-generated method stub
				lineGraph.rebuild(sharedPreferences);
				chartView.repaint();
				
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	      // TODO Auto-generated method stub
	      switch(item.getItemId()){
	      	case R.id.settings_menu_item:
	      		startActivity(new Intent(MainXYChart.this, MyPreferenceActivity.class));
	      		break;
	      		
	      	case R.id.help_menu_item:
	      		startActivity(new Intent(MainXYChart.this, HelpActivity.class));
	      		break;
	      		
	      	case R.id.save_menu_item:
	      		startActivityForResult(new Intent(this, SaveActivity.class), SAVE_ACTIVITY);
	      		break;
	      		
	      	case R.id.load_menu_item:
	      		
	      		break;
	      }
	      return super.onOptionsItemSelected(item);
	    }
	
	@Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data == null) {return;}
	    if (requestCode == SAVE_ACTIVITY){
	    	String type = data.getType();
	    	if(type.equals(SaveActivity.BITMAP_TYPE)){
	    		saveBitmap(data.getStringExtra("name"));
	    	}
	    	else{
	    		try {
					saveSortedMap(lineGraph.getSeries(), data.getStringExtra("name"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}
	    }
	  }

	/**
	 * @param v
	 * Adding new point to chart series
	 */
	public void addPoint(View v){
		
		try{
			
			Point p = new Point(Double.parseDouble(xValue.getText().toString()),
					Double.parseDouble(yValue.getText().toString()));
			lineGraph.addNewPoint(p);
		
		}catch(Exception e){
			Toast.makeText(getBaseContext(), "Type decimal values", Toast.LENGTH_LONG).show();
		}
		
		chartView.repaint();
	}
	
	
	
	public void saveBitmap(String name){
		Bitmap bitmap = chartView.toBitmap();
	    try {
	    	File file = new File(Environment.getExternalStorageDirectory(),
	    			name + ".png");
	    	FileOutputStream output = new FileOutputStream(file);
	    	bitmap.compress(CompressFormat.PNG, 100, output);

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public void saveSortedMap(Map<Double, Double> map, String name) throws Exception{

	    // Convert Map to byte array
	    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
	    ObjectOutputStream out = new ObjectOutputStream(byteOut);
	    out.writeObject(map);

	    // Parse byte array to Map (paste into load func)
	    //ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
	    //ObjectInputStream in = new ObjectInputStream(byteIn);
	    //Map<Double, Double> restoredSeries = (Map<Double, Double>) in.readObject();
	}


}
