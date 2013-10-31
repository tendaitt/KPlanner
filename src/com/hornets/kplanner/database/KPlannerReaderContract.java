package com.hornets.kplanner.database;

import android.provider.BaseColumns;

public class KPlannerReaderContract {

	//To prevent someone from accidentally instantiating the contract
		//give it an empty constructor

		public KPlannerReaderContract() {}

		/*Inner class defines table contents */
		public static abstract class KPlannerEntry implements BaseColumns{
			
			//Users table
			public static final String TABLE_NAME_USERS = "users";
			public static final String COLUMN_USER_NAME = "user_name";
			public static final String COLUMN_PASSWORD = "password";
		}
}
