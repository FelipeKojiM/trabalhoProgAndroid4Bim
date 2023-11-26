package com.example.trabalho4bimestre.model;

public class Pedidos {

    private String cliente;
    private String item;
    private int quantidade;
    private int codigo;
    private Double valorUnitario;
    private Double valorTotal;
    private Double valorTotalTodosPedidos;
    private String formaPagamento;

    public Pedidos(){
        this.cliente = cliente;
        this.item = item;
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.valorTotalTodosPedidos = valorTotalTodosPedidos;
    }

    public String getCliente() {return cliente;}

    public void setCliente(String cliente) {this.cliente = cliente;}

    public String getItem() {return item;}

    public void setItem(String item) {this.item = item;}

    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public int getCodigo() {return codigo;}

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getFormaPagamento() {return formaPagamento;}

    public void setFormaPagamento(String formaPagamento) {this.formaPagamento = formaPagamento;}

    public Double getValorUnitario() {return valorUnitario;}

    public void setValorUnitario(Double valorUnitario) {this.valorUnitario = valorUnitario;}

    public Double getValorTotal() {return valorTotal;}

    public void setValorTotal(Double valorTotal) {this.valorTotal = valorTotal;}

    public Double getValorTotalTodosPedidos() {return valorTotalTodosPedidos;}

    public void setValorTotalTodosPedidos(Double valorTotalTodosPedidos) {this.valorTotalTodosPedidos = valorTotalTodosPedidos;}
}
