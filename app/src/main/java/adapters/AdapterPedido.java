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

import com.ifma.biancamaria.alandiagourmet.AlterarPedido;
import com.ifma.biancamaria.alandiagourmet.R;

import java.util.ArrayList;

import dao.PedidoDao;
import modelo.Pedido;


public class AdapterPedido extends RecyclerView.Adapter  {

    private Context ctx;
    private ArrayList<Pedido> listapedidos;
    private PedidoDao dao;


    public AdapterPedido(Context ctx, ArrayList<Pedido> listapedidos) {
        this.ctx = ctx;
        this.listapedidos = listapedidos;
    }

    public void alterarLista(Pedido pe){
        Intent it= new Intent(ctx,  AlterarPedido.class);
        Bundle parametro = new Bundle();
        parametro.putInt("id", pe.getIdpedido());
        parametro.putString("tipo", pe.getTipo());
        parametro.putString("sabor", pe.getSabor());
        parametro.putString("tamanho", pe.getTamanho());
        it.putExtras(parametro);
        ctx.startActivity(it);
    }

    public void removerPedido(Pedido pedido){
        int position = listapedidos.indexOf(pedido);
        listapedidos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutitemrecycler = LayoutInflater.from(ctx).inflate(R.layout.item_pedido_recyclerview,null);
        ViewHolderPedido holder = new ViewHolderPedido(layoutitemrecycler);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Pedido pedido = listapedidos.get(position);
        ViewHolderPedido holderPedido = (ViewHolderPedido) holder;
        holderPedido.lblNomeCliente.setText(pedido.getNome());
        holderPedido.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarLista(pedido);
            }
        });

        holderPedido.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação").setMessage("Tem certeza que deseja excluir este pedido?");
                builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PedidoDao dao = new PedidoDao(view.getContext());
                        boolean sucesso = dao.excluir(pedido);
                        if(sucesso){
                            removerPedido(pedido);
                            Snackbar.make(view,"Excluiu!", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        }else{
                            Snackbar.make(view,"Erro ao excluir o pedido!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancelar",null).create().show();
            }
        });

    }


    @Override
    public int getItemCount() {

        return listapedidos.size();
    }


}
