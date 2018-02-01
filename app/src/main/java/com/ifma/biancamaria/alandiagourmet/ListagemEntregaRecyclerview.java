package com.ifma.biancamaria.alandiagourmet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapters.AdapterEntrega;
import dao.EntregaDao;

public class ListagemEntregaRecyclerview extends AppCompatActivity {

    private RecyclerView meurecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_entrega);

        meurecyclerview = (RecyclerView) findViewById(R.id.rcvListagemEntrega);
        LinearLayoutManager nossolayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        meurecyclerview.setLayoutManager(nossolayout);

        EntregaDao dao = new EntregaDao(this);

        AdapterEntrega meuadapter = new AdapterEntrega(this,dao.listar());

        meurecyclerview.setAdapter(meuadapter);

    }

    public void adicionaEntrega(){

    }



}
