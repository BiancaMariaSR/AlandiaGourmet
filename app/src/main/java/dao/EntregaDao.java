package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import modelo.Entrega;
import util.DBGateway;


public class EntregaDao {

    private DBGateway gw;
    private ArrayList<Entrega> listaentrega;
    private Cursor cursor;

    public EntregaDao(Context ctx){

        this.gw = DBGateway.getInstance(ctx);
    }

    public boolean salvar(Entrega entrega){
        long resultado;
        boolean retorno = false;

        ContentValues valores = new ContentValues();
        valores.put("nome",entrega.getNome());
        valores.put("endereco",entrega.getEndereco());
        valores.put("telefone",entrega.getTelefone());

        resultado = gw.getDatabase().insert("entrega",null,valores);

        if (resultado > 0)
            retorno = true;
        return retorno;
    }


    public boolean alterar(Entrega entrega){
        long resultado = 0;
        boolean retorno = false;

        ContentValues valores = new ContentValues();
        valores.put("nome",entrega.getNome());
        valores.put("endereco",entrega.getEndereco());
        valores.put("telefone",entrega.getTelefone());

        resultado = gw.getDatabase().update("entrega",valores,"identrega=?", new String[]{"" + String.valueOf(entrega.getIdentrega())});

        if (resultado > 0)
            retorno = true;
        return retorno;
    }

    public ArrayList<Entrega> listar(){
        listaentrega = new ArrayList<Entrega>();

        String colunas[] = {"identrega","nome","endereco","telefone"};
        cursor = gw.getDatabase().query("entrega",colunas,null,null,null,null,"nome");

        if (cursor.getCount()>0){

            while(cursor.moveToNext()){
                Entrega en = new Entrega();
                en.setIdentrega(cursor.getInt(0));
                en.setNome(cursor.getString(1));
                en.setEndereco(cursor.getString(2));
                en.setTelefone(cursor.getString(3));
                listaentrega.add(en);

            }
        }

        return (listaentrega);

    }


    public boolean excluir(Entrega entrega){

        long resultado = 0;
        boolean retorno = false;

        resultado = gw.getDatabase().delete("entrega","identrega=?",
                new String[]{"" + String.valueOf(entrega.getIdentrega())});
        if (resultado > 0)
            retorno = true;
        return retorno;
    }

    public Entrega retornaUltimo(){

        String colunas[] = {"nome","endereco","telefone","identrega"};
        cursor = gw.getDatabase().query("entrega",colunas,null,null,null,null,null);
        if(cursor.moveToFirst()){
            Entrega en = new Entrega();
            en.setNome(cursor.getString(0));
            en.setEndereco(cursor.getString(1));
            en.setTelefone(cursor.getString(2));
            en.setIdentrega(cursor.getInt(3));
            cursor.close();
            return en;
        }

        return null;
    }



}
