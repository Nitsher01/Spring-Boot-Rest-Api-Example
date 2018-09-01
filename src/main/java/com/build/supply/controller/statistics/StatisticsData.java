package com.build.supply.controller.statistics;

public class StatisticsData {
	
	private double sum = 0.0;
	private double avg = 0.0;
	private double max = 0.0;
	private double min = 0.0;
	private long count = 0;
		
	public StatisticsData(double statsSum, double average, double maximum, double minimum, long statsCount) {
		super();
		this.sum = statsSum;
		this.avg = average;
		this.max = maximum == Double.MIN_VALUE ? 0.0 : maximum;
		this.min = minimum == Double.MAX_VALUE ? 0.0 : minimum;
		this.count = statsCount;
	}

	public StatisticsData() {
		
	}

	@Override
	public String toString() {
		return "StatisticsData [sum=" + sum + ", avg=" + avg + ", max=" + max + ", min=" + min + ", count=" + count
				+ "]";
	}
	
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}	
	
}
