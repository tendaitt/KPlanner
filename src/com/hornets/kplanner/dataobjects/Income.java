package com.hornets.kplanner.dataobjects;

public abstract class Income {
	
	private String date;
	private String rate;
	private String hours;
	
	public Income(String date, String rate, String hours){
		
		this.date = date;
		this.rate = rate;
		this.hours = hours;
	}

	public String getDate() {
		return date;
	}

	public int getHours() {
		return 0;
	}

	public double getRate() {
		return 0;
	}
	
	
}
