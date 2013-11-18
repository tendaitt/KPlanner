
package com.hornets.kplanner.dataobjects;

import java.sql.Timestamp;

public class OtherIncome extends Income {

	private double amount = 0.0;

	public OtherIncome(Timestamp date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double getAmount(){
		return amount;
	}

	@Override
	public String toString(){

		return "Other Income:"+'\t'+ amount+'\n';
	}
}
