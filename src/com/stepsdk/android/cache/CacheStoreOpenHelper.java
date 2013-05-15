/**
 * Copyright (c) 2012 Alvin S.J. Ng
 * 
 * Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, merge, publish, 
 * distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT 
 * WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT 
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR 
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author 		Alvin S.J. Ng <email.to.alvin@gmail.com>
 * @copyright	2013	Alvin S.J. Ng
 * 
 */
package com.stepsdk.android.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CacheStoreOpenHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cache";
    private static final String KEY = "key";
    private static final String VALUE = "value";
    private static final String TYPE = "type";
    
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " TEXT, " + VALUE + " TEXT, " + TYPE + " TEXT);";
    private static final String TABLE_DROP ="DROP TABLE " + TABLE_NAME + ";";
    
    public CacheStoreOpenHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }
    
    public void drop(){
        getWritableDatabase().delete(TABLE_NAME, null, null);
    }
    
    public long put(String type, String key, String value){
        
        if(get(type, key)!=null)
            delete(type,key);
        
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY, key);
        initialValues.put(VALUE, value);
        initialValues.put(TYPE, type);

        return getWritableDatabase().insert(TABLE_NAME, null, initialValues);
    }
    
    public String get(String type, String key){
        
        String[] cols = {TYPE, KEY, VALUE};
        String[] args = {type, key};
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, cols, TYPE+"=? and "+KEY+"=?", args, null, null, null);
        
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex(VALUE));
        }
        else
            return null;

    }
    
    public boolean delete(String type, String key){
        
        String[] args = {type, key};
        int r = getWritableDatabase().delete(TABLE_NAME, TYPE+"=? and "+KEY+"=?", args);
        
        return r>0? true:false;
    }

    public String getLatest(String type) {
        String[] cols = {TYPE, KEY, VALUE};
        String[] args = {type};
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, cols, TYPE+"=?", args, null, null, null);
        
        if(cursor.getCount() > 0){
            cursor.moveToLast();
            return cursor.getString(cursor.getColumnIndex(VALUE));
        }
        else
            return null;
    }
        
}