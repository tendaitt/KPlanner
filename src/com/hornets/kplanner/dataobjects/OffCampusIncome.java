package com.hornets.kplanner.dataobjects;


public class OffCampusIncome extends Income {

	private double amount = 0.0;

	public OffCampusIncome(String date, String rate, String hours, String recurrence) {
		super(date, rate, hours, recurrence);
	}

	@Override
	public String toString() {

		return "Off Campus Income:" + '\t' + amount + '\n';
	}
		
}
