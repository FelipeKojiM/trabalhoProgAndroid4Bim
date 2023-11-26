package com.example.trabalho4bimestre.dao;

import com.example.trabalho4bimestre.model.Pedidos;

import java.util.ArrayList;

public interface IGenericDaoPedido<Object> {

    long insert(Pedidos obj);
    long update(Pedidos obj);
    long delete(Pedidos obj);
    ArrayList<Pedidos> getAll();
    Pedidos getById(int id);
}

