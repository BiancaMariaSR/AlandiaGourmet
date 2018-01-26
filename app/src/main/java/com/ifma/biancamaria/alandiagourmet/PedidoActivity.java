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

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener{

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
        btPedir.setOnClickListener(this);
        btListar = (Button)findViewById(R.id.btnListar);
        btListar.setOnClickListener(this);

    }

    public void onClick(View view){

        String nomecliente = "", tamanho = "",tipoborda = "",tiposabor = "";
        int idRadioSelecionado = 0;

        nomecliente = txtNomeCli.getText().toString();
        tamanho = spTam.getSelectedItem().toString();

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



        //Toast.makeText(this,"Clicou no botao",Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this,RecuperaPedidos.class);
        Bundle param = new Bundle();
        param.putString("nomecli",nomecliente);
        param.putString("tam",tamanho);
        param.putString("tipo",tipoborda);
        param.putString("sabor",tiposabor);
        it.putExtras(param);
        startActivity(it);


    }




}
