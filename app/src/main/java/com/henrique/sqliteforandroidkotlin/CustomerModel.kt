package com.henrique.sqliteforandroidkotlin

class CustomerModel(
    val id:Int,
    val name: String,
    val age: Int,
    val isActive: Boolean
) {
    override fun toString(): String {
        return "CustomerModel(id=$id, name='$name', age=$age, isActive=$isActive)"
    }
}