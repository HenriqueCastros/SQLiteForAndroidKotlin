package com.henrique.sqliteforandroidkotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class DAO:SQLiteOpenHelper {
    private val CUSTOMER_TABLE = "CUSTOMER"
    private val COL_CUSTOMER_NAME = "CUSTOMER_NAME"
    private val COL_CUSTOMER_AGE = "CUSTOMER_AGE"
    private val COL_ACTIVER_CUSTOMER = "ACTIVER_CUSTOMER"
    private val COL_ID = "ID"

    constructor(context: Context) : super(context, "customer.db", null, 1)

    override fun onCreate(db: SQLiteDatabase) {
        val queryString = "CREATE TABLE $CUSTOMER_TABLE ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_CUSTOMER_NAME TEXT, $COL_CUSTOMER_AGE INT, $COL_ACTIVER_CUSTOMER BOOL)"
        db.execSQL(queryString)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addOne(customerModel: CustomerModel): Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_CUSTOMER_NAME, customerModel.name)
        cv.put(COL_CUSTOMER_AGE, customerModel.age)
        cv.put(COL_ACTIVER_CUSTOMER, customerModel.isActive)

        val insert = db.insert(CUSTOMER_TABLE, null, cv)
        return (insert) != -1L
    }

    fun deleteOne(customerModel: CustomerModel) : Boolean{
        val db = this.writableDatabase
        val queryString = "DELETE FROM $CUSTOMER_TABLE WHERE $COL_ID = ${customerModel.id}"

        val cursor:Cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst())
            return true
        else
            return false
    }

    fun getAll() : List<CustomerModel>{
        val returnList = ArrayList<CustomerModel>()
        val queryString = "SELECT * FROM $CUSTOMER_TABLE"
        val db = this.readableDatabase
        val cursor =db.rawQuery(queryString, null)

        if (cursor.moveToFirst()){
            do {
                val customerId = cursor.getInt(0)
                val customerName = cursor.getString(1)
                val customerAge = cursor.getInt(2)
                val customerActive = cursor.getInt(3) != 0

                val newCustomer = CustomerModel(customerId, customerName,customerAge,customerActive)
                returnList.add(newCustomer)
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return returnList
    }
    fun getOne(id : Int) : CustomerModel?{
        var cliente:CustomerModel? = null
        val queryString = "SELECT * FROM $CUSTOMER_TABLE WHERE $COL_ID = $id"
        val db = this.readableDatabase
        val cursor =db.rawQuery(queryString, null)

        if (cursor.moveToFirst()){
            do {
                val customerId = cursor.getInt(0)
                val customerName = cursor.getString(1)
                val customerAge = cursor.getInt(2)
                val customerActive = cursor.getInt(3) != 0

                cliente = CustomerModel(customerId, customerName,customerAge,customerActive)
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return cliente
    }
}