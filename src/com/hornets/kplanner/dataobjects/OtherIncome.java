
package com.hornets.kplanner.dataobjects;

import java.sql.Timestamp;

public class OtherIncome extends Income {

	public OtherIncome(Timestamp date, String rate, String hours) {
		super(date, rate, hours);
	}

	public double calculate(){
		return 0.0;
	}
}
