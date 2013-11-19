package com.hornets.kplanner.dataobjects;


public class OnCampusIncome extends Income{

	private double amount = 0.0;
	
	public OnCampusIncome(String date, String rate, String hours,String recurrence) {
		super(date, rate, hours,recurrence);
	}
	
	@Override
	public String toString(){
		
		return "On Campus Income:"+'\t'+ amount+'\n';
	}
}
