package com.yh.chartbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;

import org.achartengine.model.XYSeries;


public class LeastSquaresEstimator {
	
	private SortedMap<Double, Double> datasetMap;

	private List<Double> xList;
	private List<Double> yList;
	
	private Double xAverage;
	private Double yAverage;
	
	//Least Squares method coefficients (y = a + b*x)
	private Double a, b;
	
	LeastSquaresEstimator(XYSeries dataset){
		
		datasetMap = dataset.getRange(dataset.getMinX(), dataset.getMaxX(), true);
		xList = new ArrayList<Double>();
		yList = new ArrayList<Double>();
		
		for (Entry<Double, Double> entry : datasetMap.entrySet())
		{
			xList.add(entry.getKey());
			yList.add(entry.getValue());
			
		}
		
		xAverage = calculateAverage(xList);
		yAverage = calculateAverage(yList);
	}
	
	
	public Double[] getCoefficients(){
		calcCoefficients(xList, yList);
		return new Double[]{a, b};
	}
	
	private void calcCoefficients(List<Double> xlist, List<Double> ylist){
		
		a = 0d;
		b = 0d;
		ListIterator<Double> xIt = xlist.listIterator();
		ListIterator<Double> yIt = ylist.listIterator();
		
		if( (!xlist.isEmpty()) && (!ylist.isEmpty())){
			Double summStDev = calcStandardDeviation(xlist);
			while(xIt.hasNext() || yIt.hasNext()){
				b += (xIt.next() - xAverage) * yIt.next();
				
			}
			b = b / summStDev;
			
			a = yAverage - b * xAverage;
			
			return;
		}
		return;
	}
	
	
	private Double calcStandardDeviation(List<Double> list){
		Double summStDev = 0d;
		
		if(!list.isEmpty()) {
		    for (Double elem : list) {
		        summStDev += Math.pow((elem - xAverage), 2);
		    }
		    return summStDev;
		  }
		  return summStDev;
	}
	
	private Double calculateAverage(List<Double> list) {
		  Double summ = 0d;
		  if(!list.isEmpty()) {
		    for (Double elem : list) {
		        summ += elem;
		    }
		    return summ / list.size();
		  }
		  return summ;
	}
	
	
	
}
