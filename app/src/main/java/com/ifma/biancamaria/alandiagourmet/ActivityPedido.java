package com.ifma.biancamaria.alandiagourmet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import dao.PedidoDao;
import modelo.Pedido;

public class ActivityPedido extends AppCompatActivity {

    private EditText txtNomeCli;
    private RadioGroup rgBordas;
    private Spinner spTam;
    private CheckBox chkFrango, chkPortuguesa, chkCalabresa, chkCarne;
    private Button btPedir, btListar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        txtNomeCli = (EditText) findViewById(R.id.txtNome);
        rgBordas = (RadioGroup) findViewById(R.id.rgBorda);
        spTam = (Spinner) findViewById(R.id.spTamanho);
        chkCalabresa = (CheckBox) findViewById(R.id.chkCalabresa);
        chkCarne = (CheckBox) findViewById(R.id.chkCarneSeca);
        chkPortuguesa = (CheckBox) findViewById(R.id.chkPortuguesa);
        chkFrango = (CheckBox) findViewById(R.id.chkFrango);
        btPedir = (Button) findViewById(R.id.btnPedir);
        btPedir.setOnClickListener(this::salvarPedido);
        btListar = (Button)findViewById(R.id.btnListar);
        btListar.setOnClickListener(this::listarPedido);
    }

    public void salvarPedido(View v){
        String nomecliente = "", tamanho = "",tipoborda = "",tiposabor = "";
        int idRadioSelecionado = 0;

        //Recuperação da aula dada no dia ministrada pelo professor
        nomecliente = txtNomeCli.getText().toString();
        tamanho = spTam.getSelectedItem().toString();
        //tipoborda= rgBordas.getCheckedRadioButtonId().toString();
        //tiposabor= ;

        idRadioSelecionado = rgBordas.getCheckedRadioButtonId();
        switch (idRadioSelecionado) {
            case (R.id.rdcBorda):
                tipoborda = "Com Borda";
                break;

            case (R.id.rdsBorda): {
                tipoborda = "Sem Borda";
                break;
            }
        }

        if(chkCalabresa.isChecked()){
            tiposabor = tiposabor + "Calabresa";
        }

        if(chkCarne.isChecked()){
            tiposabor = tiposabor + "Carne Seca";
        }

        if(chkFrango.isChecked()){
            tiposabor = tiposabor + "Frango";
        }

        if(chkPortuguesa.isChecked()){
            tiposabor = tiposabor + "Portuguesa";
        }

        //Criacao do objeto baseado na classe de modelo
        Pedido pe = new Pedido();
        pe.setNome(nomecliente);
        pe.setTamanho(tamanho);
        pe.setTipo(tipoborda);
        pe.setSabor(tiposabor);

        PedidoDao dao = new PedidoDao(getBaseContext());

        if (dao.salvar(pe)){

            Toast.makeText(this,"Dados salvos com sucesso!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Falha na Salva dos Dados!!!",Toast.LENGTH_SHORT).show();
        }
    }


    public void listarPedido(View v){

        Intent it = new Intent(this,ListagemPedidoRecyclerview.class);
        startActivity(it);

    }

}
