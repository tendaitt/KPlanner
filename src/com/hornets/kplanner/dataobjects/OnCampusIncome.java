package com.hornets.kplanner.dataobjects;

public class OnCampusIncome extends Income{

	public OnCampusIncome(String date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double calculate(){
		return 0.0;
	}
}
