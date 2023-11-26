package com.example.trabalho4bimestre.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.adapter.PedidosListaAdapter;
import com.example.trabalho4bimestre.adapter.RelatorioListaAdapter;
import com.example.trabalho4bimestre.controller.PedidosController;
import com.example.trabalho4bimestre.model.Pedidos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RelatoriosActivity extends AppCompatActivity {
    private PedidosController controller;
    private RecyclerView rvRelatorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);

        controller = new PedidosController(this);
        rvRelatorios = findViewById(R.id.rvRelatorios);

        atualizarListaRelatorios();
    }

    private void atualizarListaRelatorios () {
        ArrayList<Pedidos> listaPedidos = controller.retornarPedidos();
        RelatorioListaAdapter adapter = new RelatorioListaAdapter(listaPedidos, this);
        rvRelatorios.setLayoutManager(new LinearLayoutManager(this));
        rvRelatorios.setAdapter(adapter);
    }
}