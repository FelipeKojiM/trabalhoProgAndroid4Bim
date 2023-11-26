package com.example.trabalho4bimestre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.model.Pedidos;

import java.util.ArrayList;

public class RelatorioListaAdapter extends RecyclerView.Adapter<RelatorioListaAdapter.ViewHolder>{
    private ArrayList<Pedidos> listaPedidos;
    private Context context;

    public RelatorioListaAdapter(ArrayList<Pedidos> listaPedidos, Context context){
        this.listaPedidos = listaPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public RelatorioListaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_relatorio, parent, false);
        return new RelatorioListaAdapter.ViewHolder(listItem);
    }

    public void onBindViewHolder(@NonNull RelatorioListaAdapter.ViewHolder holder, int position) {
        Pedidos pedidoSelecionado = listaPedidos.get(position);
        holder.tvCliente.setText(pedidoSelecionado.getCliente());
        holder.tvItem.setText(pedidoSelecionado.getItem());
        holder.tvQuantidade.setText(String.valueOf(pedidoSelecionado.getQuantidade()));
        holder.tvValorUnitario.setText(String.valueOf(pedidoSelecionado.getValorUnitario()));
        holder.tvValorTotalPedido.setText(String.valueOf(pedidoSelecionado.getValorTotal()));
        holder.tvFormaPagamento.setText(pedidoSelecionado.getFormaPagamento());
        holder.tvCodigo.setText(String.valueOf(pedidoSelecionado.getCodigo()));
    }

    @Override
    public int getItemCount(){return this.listaPedidos.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCliente;
        public TextView tvItem;
        public TextView tvQuantidade;
        public TextView tvValorUnitario;
        public TextView tvValorTotalPedido;
        public TextView tvFormaPagamento;
        public TextView tvCodigo;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.tvCliente = itemView.findViewById(R.id.tvCliente);
            this.tvItem = itemView.findViewById(R.id.tvItem);
            this.tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            this.tvValorUnitario = itemView.findViewById(R.id.tvValorUnitario);
            this.tvValorTotalPedido = itemView.findViewById(R.id.tvValorTotalPedido);
            this.tvFormaPagamento = itemView.findViewById(R.id.tvFormaPagamento);
            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);

        }
    }
}