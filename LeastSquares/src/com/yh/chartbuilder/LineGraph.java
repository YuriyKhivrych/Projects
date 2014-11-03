package com.yh.chartbuilder;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.LinearLayout;



public class LineGraph {
	
	private GraphicalView view;
	
	private TimeSeries dataset = new TimeSeries("Line Graph");
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	
	//renderer to customize line 1
	private XYSeriesRenderer renderer = new XYSeriesRenderer();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	
	public LineGraph(){
		//Add single dataset to multiple dataset
		mDataset.addSeries(dataset);
		
		
		//Customize line 1
		renderer.setColor(Color.BLACK);
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setFillPoints(true);	
		
		
		mRenderer.setXTitle("x");
		mRenderer.setYTitle("y");
		mRenderer.setZoomButtonsVisible(true);
		
		
		//Add single renderer to multiple renderer
		mRenderer.addSeriesRenderer(renderer);
		
		
	}
	
	public GraphicalView getView (Context context){
		view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
		return view;
		
	}
	
	public void addNewPoint(Point point){
		dataset.add(point.getX(), point.getY());
		
	}
}
