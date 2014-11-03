package com.yh.chartbuilder;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainXYChart extends Activity implements OnClickListener {

	Button btnAdd;
	Button btnChange;
	
	private static GraphicalView mChart;
	private LineGraph lineGraph = new LineGraph();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnChange = (Button) findViewById(R.id.btnChange);
		
		btnAdd.setOnClickListener(this);
		btnChange.setOnClickListener(this);
		
		
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case(R.id.btnAdd):
				
				
				break;
			
				
		}
		
		
		
	}

}
