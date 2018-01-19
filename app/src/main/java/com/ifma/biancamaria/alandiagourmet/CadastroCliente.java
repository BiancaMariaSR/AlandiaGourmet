package com.ifma.biancamaria.alandiagourmet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import dao.ClienteDao;
import modelo.Cliente;


public class CadastroCliente extends AppCompatActivity {

    private EditText etNome;
    private EditText etEndereco;
    private EditText etTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        etNome = (EditText) findViewById(R.id.txtNome);
        etEndereco = (EditText) findViewById(R.id.txtEndereco);
        etTelefone = (EditText) findViewById(R.id.txtTelefone);


    }

    public void salvarCliente(View v){
        String nome="",endereco = "",telefone="";

        //Recuperação da aula dada no dia ministrada pelo professor
        nome = etNome.getText().toString();
        endereco = etEndereco.getText().toString();
        telefone = etTelefone.getText().toString();

        //Criacao do objeto baseado na classe de modelo
        Cliente cli = new Cliente();
        cli.setNome(nome);
        cli.setEndereco(endereco);
        cli.setTelefone(telefone);

        ClienteDao dao = new ClienteDao(getBaseContext());

        if (dao.salvar(cli)){

            Toast.makeText(this,"Dados salvos com sucesso!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Falha na Salva dos Dados!!!",Toast.LENGTH_SHORT).show();
        }
    }


    public void listarCliente(View v){

        Intent it = new Intent(this,ListagemClientesRecyclerview.class);
        startActivity(it);

    }

}
