package br.com.tech4me.produtows.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("produto")
public class Produto {
    @Id
    private String id;
    private String nome;
    private int valor;
    private int quantidadeEmEstoque;

    //#region Get / Set
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
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    //#endregion
    
    @Override
    public String toString() {
        return "Produto [id=" + id + 
               ", nome=" + nome + 
               ", quantidadeEmEstoque=" + quantidadeEmEstoque + 
               ", valor=" + valor + "]";
    }
}