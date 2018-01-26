package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import modelo.Pedido;
import util.DBGateway;

/**
 * Created by Bianca Maria on 31/12/2017.
 */

public class PedidoDao {

    private DBGateway gw;
    private ArrayList<Pedido> listadepedidos;
    private Cursor cursor;

    public PedidoDao(Context ctx){

        this.gw = DBGateway.getInstance(ctx);
    }

    public boolean salvar(Pedido pedido){
        long resultado;
        boolean retorno = false;

        ContentValues valores = new ContentValues();
        valores.put("tipo",pedido.getTipo());
        valores.put("sabor",pedido.getSabor());
        valores.put("tamanho",pedido.getTamanho());
        resultado = gw.getDatabase().insert("pedido",null,valores);

        if (resultado > 0)
            retorno = true;
        return retorno;
    }

    public ArrayList<Pedido> listar(){

        listadepedidos = new ArrayList<Pedido>();
        String colunas[] = {"tipo","sabor","tamanho"};
        cursor = gw.getDatabase().query("pedido",colunas,null,null,null,null,null);

        if (cursor.getCount()>0){

            while(cursor.moveToNext()){
                Pedido pizza = new Pedido();

                pizza.setTipo(cursor.getString(0));
                pizza.setSabor(cursor.getString(1));
                pizza.setTamanho(cursor.getString(2));
                listadepedidos.add(pizza);

            }
        }

        return (listadepedidos);

    }

}
