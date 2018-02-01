package com.ifma.biancamaria.alandiagourmet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import dao.PedidoDao;
import modelo.Pedido;

public class ActivityPizza extends AppCompatActivity {

    private TextView lblnomecliente, lblborda, lbltamanho, lblsabor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_pizza);

        String nomecliente, borda, tamanho, sabor;
        lblnomecliente = (TextView) findViewById(R.id.lblNomeText);
        lblborda = (TextView) findViewById(R.id.lblTipoBorda);
        lbltamanho = (TextView) findViewById(R.id.lblTam);
        lblsabor = (TextView) findViewById(R.id.lblSabor);

        Bundle param = getIntent().getExtras();
        nomecliente = param.getString("nomecli");
        borda = param.getString("tipo");
        tamanho = param.getString("tam");
        sabor = param.getString("sabor");

        lblnomecliente.setText(nomecliente);
        lblborda.setText(borda);
        lbltamanho.setText(tamanho);
        lblsabor.setText(sabor);

    }

    public void salvarPedido(View view){

        String tamanho = "",tipoborda = "",tiposabor = "";

        //nomecliente = lblnomecliente.getText().toString();
        tipoborda = lblborda.getText().toString();
        tamanho = lbltamanho.getText().toString();
        tiposabor = lblsabor.getText().toString();

        //Criacao do objeto baseado na classe de modelo
        Pedido pede = new Pedido();
        pede.setTipo(tipoborda);
        pede.setSabor(tiposabor);
        pede.setTamanho(tamanho);

        PedidoDao daopede = new PedidoDao(this);

        if (daopede.salvar(pede)){

            Toast.makeText(this,"Dados salvos com sucesso!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Falha na Salva dos Dados!!!",Toast.LENGTH_SHORT).show();
        }

    }

    public void listarPedido(View view){



    }
}
