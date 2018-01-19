package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bianca Maria on 30/12/2017.
 */

public class DBGateway {

    private static DBGateway gw;
    private SQLiteDatabase db;

    public DBGateway(Context ctx){
        CriaBD criabd = new CriaBD(ctx);
        this.db = criabd.getWritableDatabase();
    }

    public static DBGateway getInstance(Context ctx){

        if (gw == null)
            gw = new DBGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){

        return this.db;
    }

}
