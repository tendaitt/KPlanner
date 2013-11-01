package com.hornets.kplanner.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.hornets.kplanner.database.KPlannerReaderContract.KPlannerEntry;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version October 31 2013
 *
 */
public class KPlannerSQLHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";

	//SQL command to create users table
	private static final String SQL_CREATE_USERS=
			"CREATE TABLE " + KPlannerEntry.TABLE_NAME_USERS + " (" +
					KPlannerEntry._ID + " INTEGER PRIMARY KEY," +
					KPlannerEntry.COLUMN_USER_NAME + TEXT_TYPE + COMMA_SEP +
					KPlannerEntry.COLUMN_PASSWORD + TEXT_TYPE+ ")";

	//SQL command to drop (delete) the users table
	private static final String SQL_DELETE_USERS = 
			"DROP TABLE IF EXISTS " + KPlannerEntry.TABLE_NAME_USERS;


	private static final String DATABASE_NAME = "KPlannerUsers.db";
	private static final int DATABASE_VERSION = 1;

	/**
	 * @param context
	 * @param name - The name of the database
	 * @param factory
	 * @param version
	 */
	public KPlannerSQLHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_USERS);
	}


	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_USERS);
	}

	public Cursor getUser(SQLiteDatabase db,String username, String password)
	{
		String[] columns = {BaseColumns._ID, KPlannerEntry.COLUMN_USER_NAME, KPlannerEntry.COLUMN_PASSWORD};
		Cursor dbCursor = db.query(KPlannerEntry.TABLE_NAME_USERS,columns, KPlannerEntry.COLUMN_USER_NAME + "='" + username + "' AND " +   
				KPlannerEntry.COLUMN_PASSWORD + "='" + password + "'", null, null, null, null);  

		if (dbCursor != null) {  
			dbCursor.moveToFirst();  
		}  
		return dbCursor;  
	}  
	
	public void insertUser(SQLiteDatabase db, String username, String password)
	{
		String sql="INSERT INTO"+KPlannerEntry.TABLE_NAME_USERS+"("+KPlannerEntry.COLUMN_USER_NAME+","+KPlannerEntry.COLUMN_PASSWORD+") VALUES('"+username+"','"+password+"')";
		db.execSQL(sql);
	}

}
