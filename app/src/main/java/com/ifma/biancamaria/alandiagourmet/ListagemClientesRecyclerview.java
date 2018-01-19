package com.ifma.biancamaria.alandiagourmet;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import adapters.AdapterCliente;
import dao.ClienteDao;

public class ListagemClientesRecyclerview extends AppCompatActivity {

    private RecyclerView meurecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_clientes_recyclerview);

        meurecyclerview = (RecyclerView) findViewById(R.id.rcvListagemdeClientes);
        LinearLayoutManager nossolayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        meurecyclerview.setLayoutManager(nossolayout);

        ClienteDao dao = new ClienteDao(this);

        AdapterCliente meuadapter = new AdapterCliente(this,dao.listar());

        meurecyclerview.setAdapter(meuadapter);

    }

    public void adicionaCliente(){

    }



}
