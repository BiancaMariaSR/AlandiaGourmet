package adapters;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ifma.biancamaria.alandiagourmet.AlteraCliente;
import com.ifma.biancamaria.alandiagourmet.R;
import java.util.ArrayList;
import dao.ClienteDao;
import modelo.Cliente;

public class AdapterCliente extends RecyclerView.Adapter  {

    private Context ctx;
    private ArrayList<Cliente> listadeclientes;
    private ClienteDao dao;


    public AdapterCliente(Context ctx, ArrayList<Cliente> listadeclientes) {
        this.ctx = ctx;
        this.listadeclientes = listadeclientes;
    }

    public void alterarLista(int valor, Cliente cli){
        Intent it= new Intent(ctx,  AlteraCliente.class);
        Bundle parametro = new Bundle();
        parametro.putInt("id", cli.getIdcliente());
        parametro.putString("nome", cli.getNome());
        parametro.putString("endereco", cli.getEndereco());
        parametro.putString("telefone", cli.getTelefone());
        it.putExtras(parametro);
        ctx.startActivity(it);
    }

    public void removerCliente(Cliente cliente){
        int position = listadeclientes.indexOf(cliente);
        listadeclientes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutitemrecycler = LayoutInflater.from(ctx).inflate(R.layout.item_clientes_recyclerview,null);
        ViewHolderCliente holder = new ViewHolderCliente(layoutitemrecycler);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Cliente cli = listadeclientes.get(position);
        ViewHolderCliente holderCliente = (ViewHolderCliente) holder;
        holderCliente.lblNomeCliente.setText(cli.getNome());

        holderCliente.btnEditar.setOnClickListener(view -> alterarLista(position,cli));

        holderCliente.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este cliente?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ClienteDao dao = new ClienteDao(view.getContext());
                                boolean sucesso = dao.excluir(cli.getIdcliente());
                                if(sucesso) {
                                    removerCliente(cli);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o cliente!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return listadeclientes.size();
    }


}
