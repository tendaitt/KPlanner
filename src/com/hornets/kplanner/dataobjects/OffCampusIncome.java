package com.hornets.kplanner.dataobjects;

public class OffCampusIncome extends Income{

	public OffCampusIncome(String date, double rate, int hours) {
		super(date, rate, hours);
	}

	public double calculate(){
		return 0.0;
	}
}
