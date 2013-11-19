package com.hornets.kplanner.dataobjects;


public class OffCampusIncome extends Income {

	private double amount = 0.0;

	public OffCampusIncome(String date, String rate, String hours, String recurrence) {
		super(date, rate, hours, recurrence);
	}

	public double getAmount() {
		// get current date
		// get previous date
		// check two week schedule
		// calculate amount

		return amount;
	}

	@Override
	public String toString() {

		return "Off Campus Income:" + '\t' + amount + '\n';
	}
		
}
