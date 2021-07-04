package com.henrique.sqliteforandroidkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class EditScreen : AppCompatActivity() {
    private lateinit var dao:DAO
    private lateinit var btn_salvar : Button
    private lateinit var btn_Delete : Button
    private lateinit var et_DadosCliente : TextView
    private lateinit var et_NovoNome : TextView
    private lateinit var et_NovaIdade : TextView
    private lateinit var sw_novoActiveCustomer: Switch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_screen)

        dao = DAO(this@EditScreen)
        btn_salvar = findViewById(R.id.btn_salvar)
        btn_Delete = findViewById(R.id.btn_Delete)
        et_DadosCliente = findViewById(R.id.et_DadosCliente)
        et_NovoNome = findViewById(R.id.et_NovoNome)
        et_NovaIdade = findViewById(R.id.et_NovaIdade)
        sw_novoActiveCustomer = findViewById(R.id.sw_NovoIsActive)

        val thisIntent = intent
        val idCliente = thisIntent.extras?.getString("ID_CLIENTE").toString()
        val clienteDaPagina = dao.getOne(idCliente.toInt())
        et_DadosCliente.text = clienteDaPagina.toString()

        btn_salvar.setOnClickListener(View.OnClickListener {
            val cliente: CustomerModel? = dao.getOne(idCliente.toInt())
            val novoCliente = CustomerModel(cliente!!.id, et_NovoNome.text.toString(),
                et_NovaIdade.text.toString().toInt(), sw_novoActiveCustomer.isChecked)

            if (cliente != null) {
                if(dao.updateCliente(novoCliente))
                    Toast.makeText(this@EditScreen, "Cliente Atualizado!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this@EditScreen, "Algo de inesperado aconteceu", Toast.LENGTH_SHORT).show()
            }
        })

        btn_Delete.setOnClickListener(View.OnClickListener {
            val cliente: CustomerModel? = dao.getOne(idCliente.toInt())
            if (cliente != null) {
                dao.deleteOne(cliente)
                Toast.makeText(this@EditScreen, "Cliente excluido!", Toast.LENGTH_SHORT).show()
            }
        })

    }
}