package com.yh.chartbuilder;

import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;



public class LineGraph {
	
	private GraphicalView view;
	
	private XYSeries dataset = new XYSeries("Line Graph");
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
	
	
	/**
	 * Returns LineChartView with Dataset series and Renderer settings
	 * @param context
	 * @return GraphicalView
	 */
	public GraphicalView getView (Context context){
		view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
		return view;
		
	}
	
	/**
	 * Adds new point into chart's dataset
	 * @param point
	 */
	public void addNewPoint(Point point){
		dataset.add(point.getX(), point.getY());
	}
	
	
	/**
	 * Returns SortedMap of all graph points
	 * @return Map<Double, Double>
	 */
	public Map<Double, Double> saveSeries(){
		 return dataset.getRange(dataset.getMinX(),
				dataset.getMaxX(), true);
	}
	
}
