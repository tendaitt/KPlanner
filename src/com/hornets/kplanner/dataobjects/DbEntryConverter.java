package com.hornets.kplanner.dataobjects;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.hornets.kplanner.database.KPlannerReaderContract.KPlannerEntry;
import com.hornets.kplanner.database.KPlannerSQLHelper;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @author Mehmet Kologlu
 * @version November 2013
 * 
 * {@link DbEntryConverter} converts a database entry
 * into an appropriate Java Object
 *
 */
public class DbEntryConverter {

	private KPlannerSQLHelper dbHelper;
	private SQLiteDatabase db;
	private ArrayList<Expense> expenseArray;

	public DbEntryConverter(Context context){
		this.dbHelper = new KPlannerSQLHelper(context, "", null, 0);
		this.db = dbHelper.getReadableDatabase();
		this.expenseArray = new ArrayList<Expense>();
	}

	/**
	 * Retrieves the stored expenses
	 * @return an arrayList of expenses
	 */
	public ArrayList<Expense> getExpenseList(){

		//Columns required
		String[] projection = {
				BaseColumns._ID,
				KPlannerEntry.EXPENSE_COLUMN_NAME,
				KPlannerEntry.EXPENSE_COLUMN_TYPE,
				KPlannerEntry.EXPENSE_COLUMN_DATE,
				KPlannerEntry.EXPENSE_COLUMN_AMOUNT
		};

		String sortOrder = BaseColumns._ID + " DESC";
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
	/**
	 * Retrieved the stored other income values
	 * @return an OtherIncome object
	 */
	public OtherIncome getOtherIncome(){
		OtherIncome otherIncome = null;
		//Columns required
		String[] projection = {
				BaseColumns._ID,
				KPlannerEntry.INCOME_COLUMN_TYPE,
				KPlannerEntry.INCOME_COLUMN_HOUR,
				KPlannerEntry.INCOME_COLUMN_RATE,
				KPlannerEntry.INCOME_COLUMN_DATE,
				KPlannerEntry.INCOME_COLUMN_RECURRENCE,
		};

				String sortOrder = BaseColumns._ID + " DESC";
				Cursor c = db.query(
						KPlannerEntry.INCOME_TABLE_NAME,
						projection, 
						null, null, null, null, sortOrder);

				c.moveToFirst();

				while(!c.isAfterLast()){
					if(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_TYPE)).equalsIgnoreCase("other")){
						
					otherIncome = new OtherIncome(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_DATE)),
							c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_RATE)), 
							c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_HOUR)),
							c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_RECURRENCE)));
						
					}
					c.moveToNext();
				}
				
				return otherIncome;
	}

	/**
	 * Retrieves the store on campus information
	 * @return an OnCampus object
	 */
	public OnCampusIncome getOnCampusIncome(){
		OnCampusIncome onCampusIncome = null;
		//Columns required
		String[] projection = {
				BaseColumns._ID,
				KPlannerEntry.INCOME_COLUMN_TYPE,
				KPlannerEntry.INCOME_COLUMN_HOUR,
				KPlannerEntry.INCOME_COLUMN_RATE,
				KPlannerEntry.INCOME_COLUMN_DATE,
				KPlannerEntry.INCOME_COLUMN_RECURRENCE,
		};

		String sortOrder = BaseColumns._ID + " DESC";
		Cursor c = db.query(
				KPlannerEntry.INCOME_TABLE_NAME,
				projection, 
				null, null, null, null, sortOrder);

		c.moveToFirst();
		while(!c.isAfterLast()){
			if(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_TYPE)).equalsIgnoreCase("on")){
				
			onCampusIncome = new OnCampusIncome(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_DATE)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_RATE)), 
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_HOUR)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_RECURRENCE)));
			}
			c.moveToNext();
		}
		
		return onCampusIncome;
	}
	
	/**
	 * 	Retrieves the stored off campus object
	 * @return an off campus object
	 */
	public OffCampusIncome getOffCampusIncome(){
		OffCampusIncome offCampusIncome = null;
		//Columns required
		String[] projection = {
				BaseColumns._ID,
				KPlannerEntry.INCOME_COLUMN_TYPE,
				KPlannerEntry.INCOME_COLUMN_HOUR,
				KPlannerEntry.INCOME_COLUMN_RATE,
				KPlannerEntry.INCOME_COLUMN_DATE,
				KPlannerEntry.INCOME_COLUMN_RECURRENCE,
		};

		String sortOrder = BaseColumns._ID + " DESC";
		Cursor c = db.query(
				KPlannerEntry.INCOME_TABLE_NAME,
				projection, 
				null, null, null, null, sortOrder);

		c.moveToFirst();
		while(!c.isAfterLast()){
			if(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_TYPE)).equalsIgnoreCase("off")){
				
			offCampusIncome = new OffCampusIncome(c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_DATE)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_RATE)), 
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_HOUR)),
					c.getString(c.getColumnIndexOrThrow(KPlannerEntry.INCOME_COLUMN_RECURRENCE)));
				
			}
			c.moveToNext();
		}
		return offCampusIncome;
	}

}
