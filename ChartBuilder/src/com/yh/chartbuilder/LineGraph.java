package com.yh.chartbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Toast;



public class LineGraph {
	
	private GraphicalView view;
	
	private XYSeries dataset = new XYSeries("Line Graph");
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	
	//renderer to customize line 1
	private XYSeriesRenderer renderer = new XYSeriesRenderer();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	
	private LeastSquaresEstimator lse;
	
	
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
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setAntialiasing(true);
		
		//Add single renderer to multiple renderer
		mRenderer.addSeriesRenderer(renderer);
		
		
	}
	
	
	/**
	 * Returns LineChartView with Dataset series and Renderer settings
	 * @param Context context
	 * @return GraphicalView
	 */
	public GraphicalView getView (Context context){
		view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
		view.setBackgroundColor(Color.WHITE);
		return view;
		
	}
	
	/**
	 * Adds new point into chart's dataset
	 * @param Point point
	 */
	public void addNewPoint(Point point){
		dataset.add(point.getX(), point.getY());
	}
	
	
	/**
	 * Returns SortedMap of all graph points
	 * @return SortedMap<Double, Double>
	 */
	public Map<Double, Double> saveSeries(){
		 return dataset.getRange(dataset.getMinX(),
				dataset.getMaxX(), true);
	}
	
	
	/**
	 * 
	 * @param sharedPreferences
	 */
	public void rebuild(SharedPreferences sharedPreferences){
		
		Boolean isZoomVisible = sharedPreferences.getBoolean("zoom", true);
		Float lineWidth = Float.parseFloat(sharedPreferences.getString("line_size", "3"));
		int lineColor = Color.parseColor(sharedPreferences.getString("color", "black"));
		int psIndex = Integer.parseInt(sharedPreferences.getString("point_style", "0"));
		
		PointStyle pStyle[] = {PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.POINT,
				PointStyle.SQUARE, PointStyle.TRIANGLE, PointStyle.X};
		
		mRenderer.setZoomButtonsVisible(isZoomVisible);
		renderer.setLineWidth(lineWidth);
		mRenderer.setPointSize(2*lineWidth);
		renderer.setColor(lineColor);
		renderer.setPointStyle(pStyle[psIndex]);
	}
	
	
	
	 public void buildAproximation(){
		  lse = new LeastSquaresEstimator(dataset);
		  Double[] coeff = lse.getCoefficients();
	 }
	
	
	
	
}
