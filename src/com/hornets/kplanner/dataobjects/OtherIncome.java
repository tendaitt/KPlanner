
package com.hornets.kplanner.dataobjects;


public class OtherIncome extends Income {

	private double amount = 0.0;

	public OtherIncome(String date, String rate, String hours,String recurrence) {
		super(date, rate, hours, recurrence);
	}

	@Override
	public String toString(){

		return "Other Income:"+'\t'+ this.getAmount()+'\n';
	}
}
