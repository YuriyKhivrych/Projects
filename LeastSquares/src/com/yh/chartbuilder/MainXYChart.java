package com.yh.chartbuilder;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainXYChart extends Activity{

	EditText xValue;
	EditText yValue;
	
	private static GraphicalView mChart;
	private LineGraph lineGraph = new LineGraph();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		xValue = (EditText) findViewById(R.id.editXValue);
		yValue = (EditText) findViewById(R.id.editYValue);
		
		// Getting a reference to LinearLayout of the MainActivity Layout
	  	LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);
	  	
	  	mChart = lineGraph.getView(this);
	  	
	 	// Adding the Line Chart to the LinearLayout
	  	chartContainer.addView(mChart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void addPoint(View v){
		
		try{
			
			Point p = new Point(Double.parseDouble(xValue.getText().toString()),
					Double.parseDouble(yValue.getText().toString()));
			lineGraph.addNewPoint(p);
		
		}catch(Exception e){
			Toast.makeText(getBaseContext(), "Type decimal values", Toast.LENGTH_LONG).show();
		}
		
		mChart.repaint();
	}
	
	public void changePoints(View v){
		
	}


}
