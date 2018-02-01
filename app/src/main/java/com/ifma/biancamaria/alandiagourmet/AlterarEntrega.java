package com.ifma.biancamaria.alandiagourmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.EntregaDao;
import modelo.Entrega;


public class AlterarEntrega extends AppCompatActivity {

    private EditText etNome;
    private EditText etEndereco;
    private EditText etTelefone;
    private Button btnAlterarEntrega;
    private Entrega entrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_entrega);

        entrega = new Entrega();
        Bundle retornaParametro = getIntent().getExtras();
        entrega.setIdentrega(retornaParametro.getInt("id"));
        entrega.setNome(retornaParametro.getString("nome"));
        entrega.setEndereco(retornaParametro.getString("endereco"));
        entrega.setTelefone(retornaParametro.getString("telefone"));

        etNome = (EditText) findViewById(R.id.txtNome);
        etEndereco = (EditText) findViewById(R.id.txtEndereco);
        etTelefone = (EditText) findViewById(R.id.txtTelefone);
        btnAlterarEntrega = (Button) findViewById(R.id.btnEnviar);

        etNome.setText(entrega.getNome());
        etEndereco.setText(entrega.getEndereco());
        etTelefone.setText(entrega.getTelefone());

    }

    public void alterarEntrega(View v){
        String nome="",endereco = "",telefone="";

        //Recuperação da aula dada no dia ministrada pelo professor
        nome = etNome.getText().toString();
        endereco = etEndereco.getText().toString();
        telefone = etTelefone.getText().toString();


        entrega.setNome(nome);
        entrega.setEndereco(endereco);
        entrega.setTelefone(telefone);

        EntregaDao dao = new EntregaDao(this);

        if (dao.alterar(entrega)){

            Toast.makeText(this,"Dados salvos com sucesso!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Falha na Salva dos Dados!!!",Toast.LENGTH_SHORT).show();
        }
    }


    public void listarEntrega(View v){

        Intent it = new Intent(this, ListagemEntregaRecyclerview.class);
        startActivity(it);

    }

}
