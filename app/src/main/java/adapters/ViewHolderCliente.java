package adapters;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.ifma.biancamaria.alandiagourmet.R;

/**
 * Created by Bianca Maria on 12/01/2018.
 */

public class ViewHolderCliente extends RecyclerView.ViewHolder{

    final TextView lblNomeCliente;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;


    public ViewHolderCliente(View itemView) {
        super(itemView);
        lblNomeCliente = (TextView) itemView.findViewById(R.id.lblListaNomeCliente);
        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDelete);

    }

}
