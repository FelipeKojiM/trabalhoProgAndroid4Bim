package com.example.trabalho4bimestre.controller;

import android.content.Context;
import com.example.trabalho4bimestre.dao.PedidosDao;
import com.example.trabalho4bimestre.model.Pedidos;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PedidosController {
    private Context context;
    public PedidosController(Context context) {
        this.context = context;
    }
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public String salvarPedido(String cliente, String item, String quantidade, String valorUnitario, String formaPagamento){
        try{
            if(cliente.equals("") || cliente.isEmpty()){
                return "Informe o Nome do Cliente!";
            }
            if(item.equals("") || item.isEmpty()){
                return "Informe o Nome do Item!";
            }
            if(quantidade.equals("") || quantidade.isEmpty()){
                return "Informe a Quantidade de Itens!";
            }
            if(valorUnitario.equals("") || valorUnitario.isEmpty()){
                return "Informe o Valor do Item!";
            }
            if(formaPagamento.equals("") || formaPagamento.isEmpty()){
                return "Informe a forma de pagamento!";
            }

            Double valorItem = Double.parseDouble(valorUnitario);
            int quantidadeItem = Integer.parseInt(quantidade);
            Double valorTotal = quantidadeItem * valorItem;

                Pedidos pedidos = new Pedidos();
                pedidos.setCliente(cliente);
                pedidos.setItem(item);
                pedidos.setQuantidade(Integer.parseInt(quantidade));
                pedidos.setValorUnitario(Double.parseDouble(valorUnitario));
                pedidos.setFormaPagamento(formaPagamento);
                pedidos.setValorTotal(Double.parseDouble(df.format(valorTotal)));
                PedidosDao.getInstancia(context).insert(pedidos);

        }catch (Exception ex){
            return "Erro ao gravar Pedido."+ex.getMessage()+cliente+item+quantidade;}
        return null;
    }

    public ArrayList<Pedidos> retornarPedidos(){
        return PedidosDao.getInstancia(context).getAll();
    }
}
