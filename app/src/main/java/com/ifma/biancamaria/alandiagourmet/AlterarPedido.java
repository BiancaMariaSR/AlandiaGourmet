package com.ifma.biancamaria.alandiagourmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.PedidoDao;
import modelo.Pedido;


public class AlterarPedido extends AppCompatActivity {

    private Button btPedir, btListar;
    private Button btnAlterar;
    private EditText etTipo;
    private EditText etSabor;
    private EditText etTamanho;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_pedido);

        pedido = new Pedido();
        Bundle retornaParametro = getIntent().getExtras();
        pedido.setIdpedido(retornaParametro.getInt("id"));
        pedido.setTipo(retornaParametro.getString("tipo"));
        pedido.setSabor(retornaParametro.getString("sabor"));
        pedido.setTamanho(retornaParametro.getString("tamanho"));


        etTipo.setText(pedido.getTipo());
        etSabor.setText(pedido.getSabor());
        etTamanho.setText(pedido.getTamanho());

    }

    public void alterarPedido(View v){
        String tipo="",sabor = "",tamanho="";

        //Recuperação da aula dada no dia ministrada pelo professor
        tipo = etTipo.getText().toString();
        sabor = etSabor.getText().toString();
        tamanho = etTamanho.getText().toString();


        pedido.setTipo(tipo);
        pedido.setSabor(sabor);
        pedido.setTamanho(tamanho);

        PedidoDao dao = new PedidoDao(this);

        if (dao.alterar(pedido)){

            Toast.makeText(this,"Dados salvos com sucesso!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Falha na Salva dos Dados!!!",Toast.LENGTH_SHORT).show();
        }
    }


    public void listarPedido(View v){

        Intent it = new Intent(this, ListagemPedidoRecyclerview.class);
        startActivity(it);

    }

}
