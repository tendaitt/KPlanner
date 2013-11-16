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
		Integer myHour = new Integer(hours);
		return myHour;
	}

	public double getRate() {
		return 0;
	}
	
	
}
