package com.hornets.kplanner.database;

import android.provider.BaseColumns;

public class KPlannerReaderContract {

	//To prevent someone from accidentally instantiating the contract
		//give it an empty constructor

		public KPlannerReaderContract() {}

		/*Inner class defines table contents */
		public static abstract class KPlannerEntry implements BaseColumns{
			
			//Income table
			public static final String TABLE_NAME_INCOME = "income";
			public static final String INCOME_COLUMN_TYPE = "itype";
			public static final String INCOME_COLUMN_RATE = "rate";
			public static final String INCOME_COLUMN_HOUR = "hour";
			
			//Expense table
			public static final String TABLE_NAME_EXPENSE= "expense";
			public static final String EXPENSE_COLUMN_NAME = "name";
			public static final String EXPENSE_COLUMN_TYPE = "type";
			public static final String EXPENSE_COLUMN_DATE = "date";
			public static final String EXPENSE_COLUMN_AMOUNT = "amount";
			
		}
}
