package com.hornets.kplanner.dataobjects;

import java.sql.Timestamp;

public class OffCampusIncome extends Income{

	public OffCampusIncome(Timestamp date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double calculate(){
		//get current date
		//get previous date
		//check two week schedule
		//calculate amount
		
		return 0.0;
	}
}
