package com.hornets.kplanner.dataobjects;


public class OffCampusIncome extends Income {


	public OffCampusIncome(String date, String rate, String hours, String recurrence) {
		super(date, rate, hours, recurrence);
	}

	@Override
	public String toString() {

		return "Off Campus Income:" + '\t' + this.getAmount() + '\n';
	}
		
}
