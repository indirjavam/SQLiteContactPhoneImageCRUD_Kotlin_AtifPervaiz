package com.atifpervaiz.sqlitecontactphoneimagecrud_kotlin_atifpervaiz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// database helper class that contains all CRUD method
class MyDbHelper (context: Context?):SQLiteOpenHelper(
    context,
    Constants.DB_NAME,
    null,
    Constants.DB_VERSION
){
    override fun onCreate(db: SQLiteDatabase) {
        // create table on that db
        db.execSQL(Constants.CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // upgrade database(if there is any structure change, change db version)
        // drop older table if exists

        db.execSQL("DROP TABLE IF EXISTS"+ Constants.TABLE_NAME)
    }

    // insert record to db
    fun inserRecord(
        name:String?,
        image:String?,
        bio:String?,
        phone:String?,
        email:String?,
        dob:String?,
        addedTime:String?,
        updatedTime:String?
    ):Long{
        // get writeable database because we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        // id will be inserted automatically as we set AUTOINCREMENT in query
        // insert data
        values.put(Constants.C_NAME, name)
        values.put(Constants.C_IMAGE, image)
        values.put(Constants.C_BIO, bio)
        values.put(Constants.C_PHONE, phone)
        values.put(Constants.C_EMAIL, email)
        values.put(Constants.C_DOB, dob)
        values.put(Constants.C_ADDED_TIMESTAMP, addedTime)
        values.put(Constants.C_UPDATED_TIMESTAMP, updatedTime)

        // insert row, it will return record id of saved record
        val id = db.insert(Constants.TABLE_NAME, null, values)
        // close db connection
        db.close()
        // return id of inserted record
        return id
    }


}