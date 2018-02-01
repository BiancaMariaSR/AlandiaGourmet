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

import com.ifma.biancamaria.alandiagourmet.AlterarEntrega;
import com.ifma.biancamaria.alandiagourmet.R;

import java.util.ArrayList;

import dao.EntregaDao;
import modelo.Entrega;


public class AdapterEntrega extends RecyclerView.Adapter  {

    private Context ctx;
    private ArrayList<Entrega> listaentrega;
    private EntregaDao dao;


    public AdapterEntrega(Context ctx, ArrayList<Entrega> listaentrega) {
        this.ctx = ctx;
        this.listaentrega = listaentrega;
    }

    public void alterarLista(Entrega en){
        Intent it= new Intent(ctx,  AlterarEntrega.class);
        Bundle parametro = new Bundle();
        parametro.putInt("id", en.getIdentrega());
        parametro.putString("nome", en.getNome());
        parametro.putString("endereco", en.getEndereco());
        parametro.putString("telefone", en.getTelefone());
        it.putExtras(parametro);
        ctx.startActivity(it);
    }

    public void removerEntrega(Entrega entrega){
        int position = listaentrega.indexOf(entrega);
        listaentrega.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutitemrecycler = LayoutInflater.from(ctx).inflate(R.layout.item_entrega_recyclerview,null);
        ViewHolderEntrega holder = new ViewHolderEntrega(layoutitemrecycler);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Entrega entrega = listaentrega.get(position);
        ViewHolderEntrega holderEntrega = (ViewHolderEntrega) holder;
        holderEntrega.lblNomeEntrega.setText(entrega.getNome());
        holderEntrega.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarLista(entrega);
            }
        });

        holderEntrega.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação").setMessage("Tem certeza que deseja excluir esta entrega?");
                builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EntregaDao dao = new EntregaDao(view.getContext());
                        boolean sucesso = dao.excluir(entrega);
                        if(sucesso){
                            removerEntrega(entrega);
                            Snackbar.make(view,"Excluiu!", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        }else{
                            Snackbar.make(view,"Erro ao excluir entrega!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancelar",null).create().show();
            }
        });

    }


    @Override
    public int getItemCount() {

        return listaentrega.size();
    }


}
