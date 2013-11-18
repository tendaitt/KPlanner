package com.hornets.kplanner.dataobjects;

import java.sql.Timestamp;

public class OnCampusIncome extends Income{

	private double amount = 0.0;
	
	public OnCampusIncome(Timestamp date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double getAmount(){
		return amount;
	}
	
	@Override
	public String toString(){
		
		return "On Campus Income:"+'\t'+ amount+'\n';
	}
}
