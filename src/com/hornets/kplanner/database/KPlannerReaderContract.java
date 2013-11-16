package com.hornets.kplanner.database;

import android.provider.BaseColumns;

public class KPlannerReaderContract {

	//To prevent someone from accidentally instantiating the contract
		//give it an empty constructor

		public KPlannerReaderContract() {}

		/*Inner class defines table contents */
		public static abstract class KPlannerEntry implements BaseColumns{
			
			//Income table
			public static final String INCOME_TABLE_NAME = "income";
			public static final String INCOME_COLUMN_TYPE = "type";
			public static final String INCOME_COLUMN_RATE = "rate";
			public static final String INCOME_COLUMN_HOUR = "hour";
			public static final String TIMESTAMP = "time_stamp";
			
			//Expense table
			public static final String EXPENSE_TABLE_NAME= "expense";
			public static final String EXPENSE_COLUMN_NAME = "name";
			public static final String EXPENSE_COLUMN_TYPE = "type";
			public static final String EXPENSE_COLUMN_DATE = "date";
			public static final String EXPENSE_COLUMN_AMOUNT = "amount";
			
			
		}
}
