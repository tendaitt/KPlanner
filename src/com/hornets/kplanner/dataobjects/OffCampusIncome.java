package com.hornets.kplanner.dataobjects;

/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 24 2013
 *
 * Defines an OffCampusIncome object
 */

public class OffCampusIncome extends Income {


	public OffCampusIncome(String date, String rate, String hours, String recurrence) {
		super(date, rate, hours, recurrence);
	}

	@Override
	public String toString() {

		return "Off Campus Income:" + '\t' + this.getAmount() + '\n';
	}
		
}
