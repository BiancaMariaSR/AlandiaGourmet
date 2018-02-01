package com.ifma.biancamaria.alandiagourmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dao.EntregaDao;
import modelo.Entrega;


public class ActivityEntrega extends AppCompatActivity {

    private EditText etNome;
    private EditText etEndereco;
    private EditText etTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega);

        etNome = (EditText) findViewById(R.id.txtNome);
        etEndereco = (EditText) findViewById(R.id.txtEndereco);
        etTelefone = (EditText) findViewById(R.id.txtTelefone);


    }

    public void salvarEntrega(View v){
        String nome="",endereco = "",telefone="";

        //Recuperação da aula dada no dia ministrada pelo professor
        nome = etNome.getText().toString();
        endereco = etEndereco.getText().toString();
        telefone = etTelefone.getText().toString();

        //Criacao do objeto baseado na classe de modelo
        Entrega en = new Entrega();
        en.setNome(nome);
        en.setEndereco(endereco);
        en.setTelefone(telefone);

        EntregaDao dao = new EntregaDao(getBaseContext());

        if (dao.salvar(en)){

            Toast.makeText(this,"Dados salvos com sucesso!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Falha na Salva dos Dados!!!",Toast.LENGTH_SHORT).show();
        }
    }


    public void listarEntrega(View v){

        Intent it = new Intent(this,ListagemEntregaRecyclerview.class);
        startActivity(it);

    }

}

