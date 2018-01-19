package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.Pizza;
import util.DBGateway;

/**
 * Created by Bianca Maria on 31/12/2017.
 */

public class PizzaDao {

    private DBGateway gw;
    private ArrayList<Pizza> listapizzas;
    private Cursor cursor;

    public PizzaDao(Context ctx){

        this.gw = DBGateway.getInstance(ctx);
    }

    public boolean salvar(Pizza pizza){
        long resultado;
        boolean retorno = false;

        ContentValues valores = new ContentValues();
        valores.put("tipo",pizza.getTipo());
        valores.put("sabor",pizza.getSabor());
        valores.put("tamanho",pizza.getTamanho());
        resultado = gw.getDatabase().insert("pizza",null,valores);

        if (resultado > 0)
            retorno = true;
        return retorno;
    }

    public ArrayList<Pizza> listar(){

        listapizzas = new ArrayList<Pizza>();
        String colunas[] = {"tipo","sabor","tamanho"};
        cursor = gw.getDatabase().query("pizza",colunas,null,null,null,null,null);

        if (cursor.getCount()>0){

            while(cursor.moveToNext()){
                Pizza pizza = new Pizza();

                pizza.setTipo(cursor.getString(0));
                pizza.setSabor(cursor.getString(1));
                pizza.setTamanho(cursor.getString(2));
                listapizzas.add(pizza);

            }
        }

        return (listapizzas);

    }

}
