package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import modelo.Cliente;
import util.DBGateway;

/**
 * Created by Bianca Maria on 31/12/2017.
 */

public class ClienteDao {

    private DBGateway gw;
    private ArrayList<Cliente> listaclientes;
    private Cursor cursor;

    public ClienteDao(Context ctx){

        this.gw = DBGateway.getInstance(ctx);
    }

    public boolean salvar(Cliente cliente){
        long resultado;
        boolean retorno = false;

        ContentValues valores = new ContentValues();
        valores.put("nome",cliente.getNome());
        valores.put("endereco",cliente.getEndereco());
        valores.put("telefone",cliente.getTelefone());

        resultado = gw.getDatabase().insert("cliente",null,valores);

        if (resultado > 0)
            retorno = true;
        return retorno;
    }


    public boolean alterar(Cliente cliente){
        long resultado = 0;
        boolean retorno = false;

        ContentValues valores = new ContentValues();
        valores.put("nome",cliente.getNome());
        valores.put("endereco",cliente.getEndereco());
        valores.put("telefone",cliente.getTelefone());
        valores.put("idcliente",cliente.getIdcliente());

        resultado = gw.getDatabase().update("cliente",valores,"idcliente", new String[]{""});

        if (resultado > 0)
            retorno = true;
        return retorno;
    }

    public ArrayList<Cliente> listar(){
        listaclientes = new ArrayList<Cliente>();

        String colunas[] = {"nome","endereco","telefone"};
        cursor = gw.getDatabase().query("cliente",colunas,null,null,null,null,"nome");

        if (cursor.getCount()>0){

            while(cursor.moveToNext()){
                Cliente cli = new Cliente();
                cli.setNome(cursor.getString(0));
                cli.setEndereco(cursor.getString(1));
                cli.setTelefone(cursor.getString(2));
                listaclientes.add(cli);

            }
        }

        return (listaclientes);

    }


    public boolean excluir(int id){

        long resultado = 0;
        boolean retorno = false;

        resultado = gw.getDatabase().delete("cliente","idcliente",new String[]{id + " "});
        if (resultado > 0)
            retorno = true;
        return retorno;
    }

    public Cliente retornaUltimo(){

        String colunas[] = {"nome","endereco","telefone","idcliente"};
        cursor = gw.getDatabase().query("cliente",colunas,null,null,null,null,null);
        if(cursor.moveToFirst()){
            Cliente cli = new Cliente();
            cli.setNome(cursor.getString(0));
            cli.setEndereco(cursor.getString(1));
            cli.setTelefone(cursor.getString(2));
            cli.setIdcliente(cursor.getInt(3));
            cursor.close();
            return cli;
        }

        return null;
    }



}
