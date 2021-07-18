package br.com.tech4me.vendams.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Venda {
    @Id
    private String id;
    private String produtoVendido;
    private int quantidadeVendida;
    private LocalDate dataVendida;
    private Boolean temEmEstoque;

    public Venda() {
        setTemEmEstoque(true);
    }

    //#region Get / Set
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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
    public Boolean getTemEmEstoque() {
        return temEmEstoque;
    }
    public void setTemEmEstoque(Boolean temEmEstoque) {
        this.temEmEstoque = temEmEstoque;
    }
    //#endregion
}