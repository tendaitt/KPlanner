package com.hornets.kplanner.dataobjects;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable{

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
	public String toString() {
		return name + " | " + type + " | " + date + " | " + "$" + amount; 
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
}