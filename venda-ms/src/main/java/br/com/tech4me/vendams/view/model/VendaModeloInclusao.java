package br.com.tech4me.vendams.view.model;

import java.time.LocalDate;

public class VendaModeloInclusao {
    private String produtoVendido;
    private int quantidadeVendida;
    private LocalDate dataVendida;

    //#region Get / Set
    public String getProdutoVendido() {
        return produtoVendido;
    }
    public void setProdutoVendido(String produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
    public LocalDate getDataVendida() {
        return dataVendida;
    }
    public void setDataVendida(LocalDate dataVendida) {
        this.dataVendida = dataVendida;
    }
    //#endregion
}
