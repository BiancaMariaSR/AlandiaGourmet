package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ifma.biancamaria.alandiagourmet.R;


public class ViewHolderPedido extends RecyclerView.ViewHolder{

    final TextView lblNomeCliente;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;


    public ViewHolderPedido(View itemView) {
        super(itemView);
        lblNomeCliente = (TextView) itemView.findViewById(R.id.lblListaNomeCliente);
        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDelete);

    }

}
