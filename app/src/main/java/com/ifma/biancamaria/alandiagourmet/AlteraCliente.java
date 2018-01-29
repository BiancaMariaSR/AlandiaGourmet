package com.ifma.biancamaria.alandiagourmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.ClienteDao;
import modelo.Cliente;

/**
 * Created by Bianca Maria on 18/01/2018.
 */

public class AlteraCliente extends AppCompatActivity {

    private EditText etNome;
    private EditText etEndereco;
    private EditText etTelefone;
    private Button btnAlterar;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_cliente);

        cliente = new Cliente();
        Bundle retornaParametro = getIntent().getExtras();
        cliente.setIdcliente(retornaParametro.getInt("id"));
        cliente.setNome(retornaParametro.getString("nome"));
        cliente.setEndereco(retornaParametro.getString("endereco"));
        cliente.setTelefone(retornaParametro.getString("telefone"));

        etNome = (EditText) findViewById(R.id.txtNome);
        etEndereco = (EditText) findViewById(R.id.txtEndereco);
        etTelefone = (EditText) findViewById(R.id.txtTelefone);
        btnAlterar = (Button) findViewById(R.id.btnEnviar);

        etNome.setText(cliente.getNome());
        etEndereco.setText(cliente.getEndereco());
        etTelefone.setText(cliente.getTelefone());

    }

    public void alterarCliente(View v){
        String nome="",endereco = "",telefone="";

        //Recuperação da aula dada no dia ministrada pelo professor
        nome = etNome.getText().toString();
        endereco = etEndereco.getText().toString();
        telefone = etTelefone.getText().toString();


        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);

        ClienteDao dao = new ClienteDao(this);

        if (dao.alterar(cliente)){

            Toast.makeText(this,"Dados salvos com sucesso!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Falha na Salva dos Dados!!!",Toast.LENGTH_SHORT).show();
        }
    }


    public void listarCliente(View v){

        Intent it = new Intent(this, ListagemClientesRecyclerview.class);
        startActivity(it);

    }

}
