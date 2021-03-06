package com.hornets.kplanner.dataobjects;

import android.util.Log;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 22 2013
 * 
 * {@link Income} defines a generic Income object
 * and the basic method it can use
 */
public abstract class Income {

	private long payDate;
	private double rate;
	private double hours;
	private String recurrence;
	private double amount;

	public Income(String payDate, String rate, String hours, String recurrence) {

		this.payDate = Long.parseLong(payDate);
		this.rate = Double.parseDouble(rate);
		this.hours = Double.parseDouble(hours);
		this.recurrence = recurrence;
		amount = 0.0;
	}

	public long getpayDate() {
		return payDate;
	}

	public double getHours() {
		// Integer myHour = new Integer(hours);
		return hours;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public double getRate() {
		return rate;
	}
	
	/**
	 * Calculates the amount earned
	 * @return the amount earned
	 */
	public double getAmount(){
		
		long currentTime= System.currentTimeMillis();
		long timeElapsed = currentTime - payDate;
		Log.i("Current Time", ""+currentTime);
		Log.i("Pay Date", ""+payDate);
		Log.i("Time Elapse", ""+timeElapsed);
		int weeks = (int) (timeElapsed / (1000*60*60*24*7));
		
		//calculate the amount money in a week
		long weeklyAmount = (long) Math.floor(rate*hours);
		long monthlyAmount = 4 * weeklyAmount;
		long fortnightAmount = 2*weeklyAmount;
		int numPeriods;
		
		//calculate amount of money they have
		
		if(recurrence.equalsIgnoreCase("Bi-Weekly")){
			amount = Math.floor(weeklyAmount*weeks);
		}
		else if(recurrence.equalsIgnoreCase("monthly")){
			numPeriods = weeks/4;
			amount = numPeriods * monthlyAmount;
		}
		else{
			numPeriods = weeks/2;
			amount = numPeriods * fortnightAmount;
		}
		return amount;
		
	}

	public String getSummary() {

		return new String("Rate: "+ '\t'  + this.getRate() + '\n'+
						  "Hours: " + '\t'+ this.getHours() + '\n'+ 
						  "Recurrence: " + '\t' + this.getRecurrence()+ '\n'+
						  "Total: " + '\t' +"$"+ this.getAmount() +'\n');
	}
}
