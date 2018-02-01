package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper{

    private static final String DB_NAME = "pedepizza.db";
    private static final int DB_VERSION = 1;

    private String CREATE_PEDIDO = "CREATE TABLE pedido (idpedido INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "tipo VARCHAR (50), sabor VARCHAR (50), tamanho VARCHAR (50))";
    private String CREATE_CLIENTE = "CREATE TABLE cliente (idcliente INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nome VARCHAR (50), endereco VARCHAR (100), telefone VARCHAR (50))";
    private String CREATE_ENTREGA = "CREATE TABLE entrega (identrega INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nome VARCHAR (50), endereco VARCHAR (100), telefone VARCHAR (50))";


    public CriaBD(Context ctx){

        super(ctx,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PEDIDO);
        db.execSQL(CREATE_CLIENTE);
        db.execSQL(CREATE_ENTREGA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoantiga, int versaonova) {
        CREATE_PEDIDO = "DROP TABLE IF EXISTS pedido";
        CREATE_CLIENTE = "DROP TABLE IF EXISTS cliente";
        CREATE_ENTREGA = "DROP TABLE IF EXISTS entrega";
		
		db.execSQL(CREATE_PEDIDO);
		db.execSQL(CREATE_CLIENTE);
		db.execSQL(CREATE_ENTREGA);
		onCreate(db);
    }
}
