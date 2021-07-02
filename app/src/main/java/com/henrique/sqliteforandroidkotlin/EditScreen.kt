package com.henrique.sqliteforandroidkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EditScreen : AppCompatActivity() {
    private lateinit var dao:DAO
    private lateinit var btn_salvar : Button
    private lateinit var et_DadosCliente : TextView
    private lateinit var et_NovoNome : TextView
    private lateinit var et_NovaIdade : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_screen)

        dao = DAO(this@EditScreen)
        btn_salvar = findViewById(R.id.btn_salvar)
        et_DadosCliente = findViewById(R.id.et_DadosCliente)
        et_NovoNome = findViewById(R.id.et_NovoNome)
        et_NovaIdade = findViewById(R.id.et_NovaIdade)

        val thisIntent = intent
        val idCliente = thisIntent.extras?.getString("ID_CLIENTE").toString()
        val clienteDaPagina = dao.getOne(idCliente.toInt())
        et_DadosCliente.text = clienteDaPagina.toString()


    }
}