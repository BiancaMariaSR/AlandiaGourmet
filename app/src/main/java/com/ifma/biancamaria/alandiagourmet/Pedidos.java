package com.ifma.biancamaria.alandiagourmet;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import util.CriaBD;

public class Pedidos extends AppCompatActivity {

    private TextView lblnomecliente, lblborda, lbltamanho, lblsabor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_pedidos);

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

        String nomecliente = "", tamanho = "",tipoborda = "",tiposabor = "";

        nomecliente = lblnomecliente.getText().toString();
        tipoborda = lblborda.getText().toString();
        tamanho = lbltamanho.getText().toString();
        tiposabor = lblsabor.getText().toString();

        CriaBD criabd = new CriaBD(this);
        SQLiteDatabase db = criabd.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("cliente",nomecliente);
        valores.put("tipo",tipoborda);
        valores.put("sabor",tiposabor);
        valores.put("tamanho",tamanho);
        db.insert("pizza",null,valores);
        Toast.makeText(this,"Seu pedido já foi realizado!",Toast.LENGTH_SHORT).show();
    }

    public void listarPedido(View view){
        //Array contendo as colunas que serão usadas no SELECT
        String colunas[] = {"cliente","tipo","sabor","tamanho"};
        CriaBD criabd = new CriaBD(this);

        //Recuperamos uma referência do banco de dados para apenas leitura
        SQLiteDatabase db = criabd.getReadableDatabase();

        //Utilização de um objeto cursor para iterar pelos valores
        //retornados na execução do SELECT
        Cursor cursor = db.query(false,"pizza",colunas,null,null,null,null,null,null);

        //Verificamos se a quantidade de registros retornados é maior que 0
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                //Recuperamos a coluna "nome", se quisÃ©ssemos a nota por exemplo
                //usarÃ­amos cursor.getInt(3), atribuindo este a uma variÃ¡vel inteira
                String nomecliente = cursor.getString(1);
                String tipopizza = cursor.getString(2);
                String saborpizza = cursor.getString(3);
                String tamanho = cursor.getString(4);
                Toast.makeText(this,nomecliente,Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,saborpizza,Toast.LENGTH_SHORT).show();
            }
        }


    }
}
