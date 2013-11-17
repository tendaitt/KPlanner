package com.hornets.kplanner.dataobjects;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hornets.kplanner.database.KPlannerReaderContract.KPlannerEntry;
import com.hornets.kplanner.database.KPlannerSQLHelper;

public class DbEntryConverter {

	private KPlannerSQLHelper dbHelper;
	private SQLiteDatabase db;
	private ArrayList<Expense> expenseArray;

	public DbEntryConverter(Context context){
		this.dbHelper = new KPlannerSQLHelper(context, "", null, 0);
		this.db = dbHelper.getReadableDatabase();
		this.expenseArray = new ArrayList<Expense>();
	}

	public ArrayList<Expense> getExpenseList(){

		//Columns required
		String[] projection = {
				KPlannerEntry._ID,
				KPlannerEntry.EXPENSE_COLUMN_NAME,
				KPlannerEntry.EXPENSE_COLUMN_TYPE,
				KPlannerEntry.EXPENSE_COLUMN_DATE,
				KPlannerEntry.EXPENSE_COLUMN_AMOUNT
		};

		String sortOrder = KPlannerEntry._ID + " DESC";
		Cursor c = db.query(
				KPlannerEntry.EXPENSE_TABLE_NAME,
				projection, 
				null, null, null, null, sortOrder);

		c.moveToFirst();

		while(!c.isAfterLast()){
			Expense expense = new Expense(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.EXPENSE_COLUMN_NAME)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.EXPENSE_COLUMN_TYPE)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.EXPENSE_COLUMN_DATE)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.EXPENSE_COLUMN_AMOUNT)));
			expenseArray.add(expense);
			c.moveToNext();
		}
		
		return expenseArray;
	}

	public OnCampusIncome getOnCampusIncome(){
		
		//Columns required
		String[] projection = {
				KPlannerEntry._ID,
				KPlannerEntry.INCOME_COLUMN_TYPE,
				KPlannerEntry.INCOME_COLUMN_HOUR,
				KPlannerEntry.INCOME_COLUMN_RATE,
		};

		String sortOrder = KPlannerEntry._ID + " DESC";
		Cursor c = db.query(
				KPlannerEntry.EXPENSE_TABLE_NAME,
				projection, 
				null, null, null, null, sortOrder);

		c.moveToFirst();

		while(!c.isAfterLast()){
			if(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_TYPE)).equals("ON"))
			{
			OnCampusIncome income = new OnCampusIncome(
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_TYPE)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_HOUR)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_RATE)));
			return income;
			}
		}
		//if no on-campus income exists
		return null;
	}

}