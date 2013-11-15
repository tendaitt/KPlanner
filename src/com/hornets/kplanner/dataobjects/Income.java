package com.hornets.kplanner.dataobjects;

public abstract class Income {
	
	private String date;
	private double rate;
	private int hours;
	
	public Income(String date, double rate, int hours){
		
		this.date = date;
		this.rate = rate;
		this.hours = hours;
	}

	public String getDate() {
		return date;
	}

	public int getHours() {
		return hours;
	}

	public double getRate() {
		return rate;
	}
	
}
