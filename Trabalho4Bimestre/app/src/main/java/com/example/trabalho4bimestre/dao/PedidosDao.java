package com.example.trabalho4bimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.trabalho4bimestre.helper.SQLiteDataHelper;
import com.example.trabalho4bimestre.model.Pedidos;
import com.example.trabalho4bimestre.view.PedidosActivity;

import java.util.ArrayList;

public class PedidosDao  implements IGenericDaoPedido<PedidosActivity> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = { "CODIGO", "CLIENTE", "ITEM", "QUANTIDADE", "VALORUNITARIO", "FORMAPAGAMENTO", "VALORTOTALITEMPEDIDO", "VALORTOTALPEDIDOS"};
    private String tabela = "PEDIDO";
    private Context context;
    private static PedidosDao instancia;

    public static PedidosDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new PedidosDao(context);
        }else{
            return instancia;
        }
    }

    private PedidosDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "BANCO_BD", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Pedidos obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getCliente());
            valores.put(colunas[2], obj.getItem());
            valores.put(colunas[3], obj.getQuantidade());
            valores.put(colunas[4], obj.getValorUnitario());
            valores.put(colunas[5], obj.getFormaPagamento());
            valores.put(colunas[6], obj.getValorTotal());
            valores.put(colunas[7], obj.getValorTotalTodosPedidos());
            return baseDados.insert(tabela, null, valores);
        }catch (SQLException ex){
            Log.e("PEDIDO", "ERRO: PedidosDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Pedidos obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getCliente());
            valores.put(colunas[2], obj.getItem());
            valores.put(colunas[3], obj.getQuantidade());
            valores.put(colunas[4], obj.getValorUnitario());
            valores.put(colunas[5], obj.getFormaPagamento());
            valores.put(colunas[6], obj.getValorTotal());
            valores.put(colunas[7], obj.getValorTotalTodosPedidos());
            String[]identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.update(tabela,  valores, colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("PEDIDO", "ERRO: PedidosDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pedidos obj) {
        try{
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return baseDados.delete(tabela, colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("PEDIDO", "ERRO: PedidosDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Pedidos> getAll() {
        ArrayList<Pedidos> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela, colunas, null, null, null, null, colunas[0]+" asc");

            if(cursor.moveToFirst()){
                do{
                    Pedidos pedidos = new Pedidos();
                    pedidos.setCodigo(cursor.getInt(0));
                    pedidos.setCliente(cursor.getString(1));
                    pedidos.setItem(cursor.getString(2));
                    pedidos.setQuantidade(cursor.getInt(3));
                    pedidos.setValorUnitario(cursor.getDouble(4));
                    pedidos.setFormaPagamento(cursor.getString(5));
                    pedidos.setValorTotal(cursor.getDouble(6));
                    pedidos.setValorTotalTodosPedidos(cursor.getDouble(7));

                    lista.add(pedidos);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("PEDIDO", "ERRO: PedidosDao.getAll() "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Pedidos getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas, colunas[0]+"= ?", identificador, null, null, null);

            if(cursor.moveToFirst()){
                Pedidos pedidos = new Pedidos();
                pedidos.setCliente(cursor.getString(1));
                pedidos.setItem(cursor.getString(2));
                pedidos.setQuantidade(cursor.getInt(3));
                pedidos.setValorUnitario(cursor.getDouble(4));
                pedidos.setFormaPagamento(cursor.getString(5));
                pedidos.setValorTotal(cursor.getDouble(6));
                pedidos.setValorTotalTodosPedidos(cursor.getDouble(7));

                return pedidos;
            }

        }catch (SQLException ex){
            Log.e("PEDIDO", "ERRO: Pedidos.getById() "+ex.getMessage());
        }
        return null;
    }
}

