
package com.evolet.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.evolet.myapplication.Items.Materials;
import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.Items.UserDetails;


import java.util.ArrayList;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "android_api";

	// Login table name
	private static final String TABLE_USER = "user";
	private static final String TABLE_CART="cart";
	private static final String TABLE_USERDETAIL="userDetail";
	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String MATERIAL_NAME = "material_name";
	private static final String MATERIAL_UNIT = "material_unit";
	private static final String MATERIAL_PRICE = "material_price";
	private static final String MATERIAL_CATEGORY = "material_category";
	private static final String KEY_USERNAME="username";
	private static final String KEY_USERPHONE="userphone";
	private static final String KEY_USERADDRESS="useraddress";

	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ MATERIAL_NAME + " TEXT NOT NULL UNIQUE,"
				+ MATERIAL_UNIT + " TEXT NOT NULL UNIQUE,"
				+ MATERIAL_PRICE + " TEXT NOT NULL,"
				+ MATERIAL_CATEGORY +" TEXT" + ")";
		db.execSQL(CREATE_LOGIN_TABLE);


		String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ MATERIAL_NAME + " TEXT NOT NULL UNIQUE,"
				+ MATERIAL_UNIT + " INTEGER,"
				+ MATERIAL_PRICE + " INTEGER,"
				+ MATERIAL_CATEGORY +" TEXT" + ")";
		db.execSQL(CREATE_CART_TABLE);

		String CREATE_ADDRESS_TABLE = "CREATE TABLE " + TABLE_USERDETAIL + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_USERNAME + " TEXT NOT NULL UNIQUE,"
				+ KEY_USERPHONE + " TEXT,"
				+ KEY_USERADDRESS +" TEXT" + ")";
		db.execSQL(CREATE_ADDRESS_TABLE);
		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public boolean addProducts(String name, String unit, String price, String category) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(MATERIAL_NAME, name); // Name
		values.put(MATERIAL_UNIT, unit); // Email
		values.put(MATERIAL_PRICE, price); // Email
		//values.put(KEY_INFO, appInfo);
		values.put(MATERIAL_CATEGORY,category);// Created At
		//values.put(KEY_FOLDER,appFolder);

		// Inserting Row
		long id = db.insertWithOnConflict(TABLE_USER, null, values,SQLiteDatabase.CONFLICT_REPLACE);
		db.close(); // Closing database connection
		Log.d(TAG, "New user inserted into sqlite: " + id);
	return true;
	}


	public ArrayList<ProductItem> getAppList(){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> chapterList = null;
		try{
			chapterList = new ArrayList<ProductItem>();
			String QUERY = "SELECT material_name,material_price,material_unit,material_category FROM user ";
			Cursor cursor = db.rawQuery(QUERY, null);
		//	if(!cursor.moveToFirst())
			//{

				while (cursor.moveToNext())
				{
					ProductItem chapter = new ProductItem(null,null,null,null);
					chapter.setProdName(cursor.getString(0));
					chapter.setProdPrice(cursor.getString(1));
					chapter.setUnit(cursor.getString(2));
					chapter.setProdCategory(cursor.getString(3));
					chapterList.add(chapter);
					Log.d("@@@","adapter="+chapterList.size());

				}
		//	}
			db.close();
		}catch (Exception e){
			Log.e("error", e + "");
		}
		return chapterList;
	}


	public ArrayList<ProductItem> getProductDetails(int pos) {

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> imageList = null;
		try {
			imageList = new ArrayList<ProductItem>();
			String QUERY = "SELECT material_name,material_price,material_unit,material_category FROM user where id='" + pos + "'";

			//String QUERY = "SELECT  * FROM " + TABLE_USER;
			//memberName name changes
			Cursor mCursor = db.rawQuery(QUERY, null);
			 	if (!mCursor.moveToFirst()) {
					while (mCursor.moveToNext()) {
						ProductItem images = new ProductItem(null, null, null, null);
						images.setProdName(mCursor.getString(0));
						images.setProdPrice(mCursor.getString(1));
						images.setUnit(mCursor.getString(2));
						images.setProdCategory(mCursor.getString(3));
						//images.setApp_type(mCursor.getString(4));
						//images.setAppFolder(mCursor.getString(5));
						imageList.add(images);

					}
				}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}
	public ArrayList<ProductItem> getDetails(String titleName) {

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> imageList = null;
		try {
			imageList = new ArrayList<ProductItem>();
			String QUERY = "SELECT material_name,material_price,material_unit,material_category FROM user where material_category='" + titleName + "'";

			//String QUERY = "SELECT  * FROM " + TABLE_USER;
			 //memberName name changes
			Cursor mCursor = db.rawQuery(QUERY, null);
		//	if (!mCursor.moveToFirst()) {
				while (mCursor.moveToNext()) {
					ProductItem images = new ProductItem(null,null,null,null);
					images.setProdName(mCursor.getString(0));
					images.setProdPrice(mCursor.getString(1));
					images.setUnit(mCursor.getString(2));
					images.setProdCategory(mCursor.getString(3));
					//images.setApp_type(mCursor.getString(4));
					//images.setAppFolder(mCursor.getString(5));
					imageList.add(images);

				}
			//}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}
	public void removeSingleContent(String title) {
		//Open the database
		SQLiteDatabase database = this.getWritableDatabase();
		database.execSQL("DELETE FROM   user   WHERE material_name  = '" + title + "'");
		database.close();
	}


	public ArrayList<ProductItem> getProductItem(String titleName) {

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> imageList = null;
		try {
			imageList = new ArrayList<ProductItem>();
			String QUERY = "SELECT material_name,material_price,material_unit,material_category FROM user where material_name='" + titleName + "'";

			//String QUERY = "SELECT  * FROM " + TABLE_USER;
			//memberName name changes
			Cursor mCursor = db.rawQuery(QUERY, null);
			//	if (!mCursor.moveToFirst()) {
			while (mCursor.moveToNext()) {
				ProductItem images = new ProductItem(null,null,null,null);
				images.setProdName(mCursor.getString(0));
				images.setProdPrice(mCursor.getString(1));
				images.setUnit(mCursor.getString(2));
				images.setProdCategory(mCursor.getString(3));
				//images.setApp_type(mCursor.getString(4));
				//images.setAppFolder(mCursor.getString(5));
				imageList.add(images);

			}
			//}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}
	/*public ArrayList<DataModel> getUnd(String apptype ) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<DataModel> imageList = null;
		try {
			imageList = new ArrayList<DataModel>();
			String QUERY = "SELECT name,value,app_id,appInfo,app_type FROM user where app_type='" + apptype+ "' ";
			Cursor mCursor = db.rawQuery(QUERY, null);

			if (!mCursor.isLast()) {
				while (mCursor.moveToNext()) {
					DataModel images = new DataModel(null,null,null,null,null);
					images.setName(mCursor.getString(0));
					images.setValue(mCursor.getString(1));
					images.setApp_id(mCursor.getString(2));
					images.setAppInfo(mCursor.getString(3));
					images.setApp_type(mCursor.getString(4));
					imageList.add(images);
				}
			}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}
*/

	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}
	public boolean addToCart(String name, int unit, int price, String category) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(MATERIAL_NAME, name); // Name
		values.put(MATERIAL_UNIT, unit); // unit
		values.put(MATERIAL_PRICE, price); // price
		values.put(MATERIAL_CATEGORY,category);// Created At
		//values.put(KEY_FOLDER,appFolder);

		// Inserting Row
		long id = db.insertWithOnConflict(TABLE_CART, null, values,SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		Log.d(TAG, "Cart item inserted into sqlite: " + id);
		return true;
	}

	public boolean addUserDetail(String userName, String userPhone, String userAddress) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, userName); // Name
		values.put(KEY_USERPHONE, userPhone); // unit
		values.put(KEY_USERADDRESS, userAddress); // price

		// Inserting Row
		long id = db.insertWithOnConflict(TABLE_USERDETAIL, null, values,SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		Log.d(TAG, "Address item inserted into sqlite: " + id);
		return true;
	}

	public ArrayList<UserDetails> showUserDetail() {

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<UserDetails> imageList = null;
		try {
			imageList = new ArrayList<UserDetails>();
			String QUERY = "SELECT username,userphone,useraddress FROM userDetail ";
			Cursor mCursor = db.rawQuery(QUERY, null);
			while (mCursor.moveToNext()) {
				UserDetails images = new UserDetails(null,null,null);
				images.setmUserName(mCursor.getString(0));
				images.setmPhone(mCursor.getString(1));
				images.setmAddress(mCursor.getString(2));
				imageList.add(images);

			}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}


	public boolean updateData(String name, int unit, int price, String category) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MATERIAL_NAME, name); // Name
		values.put(MATERIAL_UNIT, unit); // unit
		values.put(MATERIAL_PRICE, price); // price
		values.put(MATERIAL_CATEGORY,category);// Created At
		db.update(TABLE_CART, values, "material_name = ?",new String[] { name });
		return true;
	}
	public ArrayList<ProductItem> showCartItem(String titleName) {

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> imageList = null;
		try {
			imageList = new ArrayList<ProductItem>();
			String QUERY = "SELECT material_name,material_price,material_unit,material_category FROM cart where material_name='" + titleName + "'";
			Cursor mCursor = db.rawQuery(QUERY, null);
			while (mCursor.moveToNext()) {
				ProductItem images = new ProductItem(null,null,null,null);
				images.setProdName(mCursor.getString(0));
				images.setProdPrice(mCursor.getString(1));
				images.setUnit(mCursor.getString(2));
				images.setProdCategory(mCursor.getString(3));
				imageList.add(images);

			}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}

	public ArrayList<ProductItem> Caclutate() {

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> imageList = null;
		try {
			imageList = new ArrayList<ProductItem>();
			String QUERY = "SELECT SUM material_price FROM cart";
			Cursor mCursor = db.rawQuery(QUERY, null);
			while (mCursor.moveToNext()) {
				ProductItem images = new ProductItem(null,null,null,null);
				images.setProdPrice(mCursor.getString(0));
				imageList.add(images);

			}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}
	public ArrayList<ProductItem> showCartItem() {

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> imageList = null;
		try {
			imageList = new ArrayList<ProductItem>();
			String QUERY = "SELECT material_name,material_price,material_unit,material_category FROM cart";
			Cursor mCursor = db.rawQuery(QUERY, null);
			while (mCursor.moveToNext()) {
				ProductItem images = new ProductItem(null,null,null,null);
				images.setProdName(mCursor.getString(0));
				images.setProdPrice(mCursor.getString(1));
				images.setUnit(mCursor.getString(2));
				images.setProdCategory(mCursor.getString(3));
				imageList.add(images);

			}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return imageList;
	}
	public boolean checkExists(String name){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ProductItem> imageList = null;
		try {
			imageList = new ArrayList<ProductItem>();
			String QUERY = "SELECT * FROM cart where material_name='" + name + "'";

			//String QUERY = "SELECT  * FROM " + TABLE_USER;
			//memberName name changes
			Cursor mCursor = db.rawQuery(QUERY, null);
			//	if (!mCursor.moveToFirst()) {
			while (mCursor.moveToNext()) {
				ProductItem images = new ProductItem(null,null,null,null);
				images.setProdName(mCursor.getString(0));
				images.setProdPrice(mCursor.getString(1));
				images.setUnit(mCursor.getString(2));
				images.setProdCategory(mCursor.getString(3));
				imageList.add(images);

			}
			//}
			db.close();
		} catch (Exception e) {
			Log.e("TITLE NAME", e + "" + e.getMessage());
		}
		return true;
	}

	public  boolean CheckIsDataAlreadyInDBorNot(String TableName,
													  String dbfield, String fieldValue) {
		SQLiteDatabase sqldb =this.getReadableDatabase();
		String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
		Cursor cursor = sqldb.rawQuery(Query, null);
		if(cursor.getCount() <= 0){
			cursor.close();
			return false;
		}
		cursor.close();
		return true;
	}
}
