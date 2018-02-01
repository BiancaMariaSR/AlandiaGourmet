package adapters;
import com.ifma.biancamaria.alandiagourmet.AlterarCliente;
import com.ifma.biancamaria.alandiagourmet.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
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

    public void alterarLista(Cliente cli){
        Intent it= new Intent(ctx,  AlterarCliente.class);
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

        final Cliente cliente = listadeclientes.get(position);
        ViewHolderCliente holderCliente = (ViewHolderCliente) holder;
        holderCliente.lblNomeCliente.setText(cliente.getNome());
        holderCliente.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarLista(cliente);
            }
        });

        holderCliente.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação").setMessage("Tem certeza que deseja excluit este cliente?");
                builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ClienteDao dao = new ClienteDao(view.getContext());
                        boolean sucesso = dao.excluir(cliente);
                        if(sucesso){
                            removerCliente(cliente);
                            Snackbar.make(view,"Excluiu!", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        }else{
                            Snackbar.make(view,"Erro ao excluir o cliente!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancelar",null).create().show();
            }
        });

    }


    @Override
    public int getItemCount() {

        return listadeclientes.size();
    }


}
