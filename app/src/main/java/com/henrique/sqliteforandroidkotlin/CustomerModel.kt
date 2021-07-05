package com.henrique.sqliteforandroidkotlin

class CustomerModel(
    var id:Int,
    var name: String,
    var age: Int,
    var isActive: Boolean
) {
    override fun toString(): String {
        return "CustomerModel(id=$id, name='$name', age=$age, isActive=$isActive)"
    }
}