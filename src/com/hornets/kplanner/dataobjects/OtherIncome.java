

package com.hornets.kplanner.dataobjects;

public class OtherIncome extends Income {

	public OtherIncome(String date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double calculate(){
		return 0.0;
	}
}
