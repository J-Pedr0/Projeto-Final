package br.com.tech4me.produtows.view.model;

import java.util.List;

import br.com.tech4me.produtows.shared.Venda;

public class ProdutoResponseDetalhes {
    private String id;
    private String nome;
    private int valor;
    private List<Venda> venda;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public List<Venda> getVenda() {
        return venda;
    }
    public void setVenda(List<Venda> venda) {
        this.venda = venda;
    }

    
}
