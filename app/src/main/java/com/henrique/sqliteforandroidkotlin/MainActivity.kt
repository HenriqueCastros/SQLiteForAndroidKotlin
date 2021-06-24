package com.henrique.sqliteforandroidkotlin

import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var dao:DAO
    private lateinit var customerArrayadapter: ArrayAdapter<CustomerModel>
    private lateinit var btn_add: Button
    private lateinit var btn_viewAll: Button
    private lateinit var et_name: EditText
    private lateinit var et_age: EditText
    private lateinit var sw_activeCustomer: Switch
    private lateinit var lv_customerList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add = findViewById(R.id.btn_add)
        btn_viewAll = findViewById(R.id.btn_viewAll)
        et_age = findViewById(R.id.et_age)
        et_name = findViewById(R.id.et_name)
        sw_activeCustomer = findViewById(R.id.sw_active)
        lv_customerList = findViewById(R.id.lv_customerList)

        dao = DAO(this@MainActivity)

        btn_add.setOnClickListener(View.OnClickListener {
            lateinit var customerModel:CustomerModel
            try{
                customerModel = CustomerModel(-1, et_name.text.toString(),
                   (et_age.text.toString().toInt()), sw_activeCustomer.isChecked)
            }catch (e: Exception){
                Toast.makeText(this@MainActivity, "Error crating $e ${sw_activeCustomer.isChecked}", Toast.LENGTH_SHORT)
                    .show()
                customerModel = CustomerModel(-1, "error", 0 , false);
            }

            dao = DAO(this@MainActivity)
            val success = dao.addOne(customerModel)
            Toast.makeText(this@MainActivity, "Success = $success", Toast.LENGTH_SHORT)
                .show()
            showCustomers(dao)
        })

        btn_viewAll.setOnClickListener(View.OnClickListener {
            dao = DAO(this@MainActivity)
            showCustomers(dao)
        })

        lv_customerList.setOnItemClickListener{parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as CustomerModel
            dao.deleteOne(element)
            showCustomers(dao)
        }
    }

    private fun showCustomers(dao2: DAO) {
        customerArrayadapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_list_item_1, dao2.getAll()
        )
        lv_customerList.setAdapter(customerArrayadapter)
    }
}