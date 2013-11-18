package com.hornets.kplanner.dataobjects;

import java.sql.Timestamp;

public abstract class Income {

	private Timestamp date;
	private String rate;
	@SuppressWarnings("unused")
	private String hours;

	public Income(Timestamp date, String rate, String hours) {

		this.date = date;
		this.rate = rate;
		this.hours = hours;
	}

	public Timestamp getDate() {
		return date;
	}

	public int getHours() {
		// Integer myHour = new Integer(hours);
		return 0;
	}

	public double getRate() {
		return Double.parseDouble(rate);
	}

	public String getSummary() {

		return new String("Type:" + '\t' + "Off Campus" + '\n' + "Rate: "
				+ '\t' + this.getRate() + '\n' + "Hours: " + '\t'
				+ this.getHours() + '\n' + "Recurrence: " + '\n' + "Weekly"
				+ '\n');
	}
}
