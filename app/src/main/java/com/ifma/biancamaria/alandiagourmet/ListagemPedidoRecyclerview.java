package com.ifma.biancamaria.alandiagourmet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapters.AdapterPedido;
import dao.PedidoDao;

public class ListagemPedidoRecyclerview extends AppCompatActivity {

    private RecyclerView meurecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_pedido);

        meurecyclerview = (RecyclerView) findViewById(R.id.rcvListagemPedido);
        LinearLayoutManager nossolayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        meurecyclerview.setLayoutManager(nossolayout);

        PedidoDao dao = new PedidoDao(this);

        AdapterPedido meuadapter = new AdapterPedido(this,dao.listar());

        meurecyclerview.setAdapter(meuadapter);

    }

    public void adicionaPedido(){

    }



}
