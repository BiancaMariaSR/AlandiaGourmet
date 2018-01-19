package modelo;

/**
 * Created by Bianca Maria on 31/12/2017.
 */

public class Pedido {

    private int fkidcliente;
    private int fkidpizza;

    public int getFkidcliente() {
        return fkidcliente;
    }

    public void setFkidcliente(int fkidcliente) {
        this.fkidcliente = fkidcliente;
    }

    public int getFkidpizza() {
        return fkidpizza;
    }

    public void setFkidpizza(int fkidpizza) {
        this.fkidpizza = fkidpizza;
    }
}
