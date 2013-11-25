package com.hornets.kplanner.dataobjects;

/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 24 2013
 * 
 * Defines the OnCampus Income Object
 *
 */
public class OnCampusIncome extends Income{

	public OnCampusIncome(String date, String rate, String hours,String recurrence) {
		super(date, rate, hours,recurrence);
	}
	
	@Override
	public String toString(){
		
		return "On Campus Income:"+'\t'+ this.getAmount()+'\n';
	}
}
