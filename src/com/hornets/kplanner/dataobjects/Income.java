package com.hornets.kplanner.dataobjects;


public abstract class Income {

	private String date;
	private String rate;
	private String hours;
	private String recurrence;

	public Income(String date, String rate, String hours, String recurrence) {

		this.date = date;
		this.rate = rate;
		this.hours = hours;
		this.recurrence = recurrence;
	}

	public String getDate() {
		return date;
	}

	public String getHours() {
		// Integer myHour = new Integer(hours);
		return hours;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public double getRate() {
		return Double.parseDouble(rate);
	}

	public String getSummary() {

		return new String("Type:" + '\t' + "Off Campus" + '\n' + "Rate: "
				+ '\t' + this.getRate() + '\n' + "Hours: " + '\t'
				+ this.getHours() + '\n' + "Recurrence: " + '\n' + this.getRecurrence()
				+ '\n');
	}
}
