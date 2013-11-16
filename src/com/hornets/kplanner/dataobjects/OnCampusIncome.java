package com.hornets.kplanner.dataobjects;

import java.sql.Timestamp;

public class OnCampusIncome extends Income{

	public OnCampusIncome(Timestamp date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double calculate(){
		return 0.0;
	}
}
