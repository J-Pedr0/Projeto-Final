package br.com.tech4me.vendams.view.model;

import java.time.LocalDate;

public class VendaModeloAlteracao {
    private String id;
    private int quantidadeVendida;
    private LocalDate dataVendida;
    private Boolean temEmEstoque;

    //#region Get / Set
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
