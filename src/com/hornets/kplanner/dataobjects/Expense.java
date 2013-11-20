package com.hornets.kplanner.dataobjects;

public class Expense {

	private String name;
	private String type;
	private String date;
	private String amount;
	public Expense(String n, String t, String d, String a) {
		name = n;
		type = t;
		date = d;
		amount = a;
	}

	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getDate() {
		return date;
	}
	public String getAmount() {
		return amount;
	}
}
