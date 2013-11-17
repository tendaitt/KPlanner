package com.hornets.kplanner.dataobjects;

import java.sql.Timestamp;

public class OffCampusIncome extends Income{

	private double amount =0;
	public OffCampusIncome(Timestamp date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double getAmount(){
		//get current date
		//get previous date
		//check two week schedule
		//calculate amount
		
		return amount;
	}
	
@Override
public String toString(){
		
		return "Off Campus Income:"+ '\t'+amount+'\n';
	}
}
