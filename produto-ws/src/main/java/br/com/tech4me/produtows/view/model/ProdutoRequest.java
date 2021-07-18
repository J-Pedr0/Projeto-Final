package br.com.tech4me.produtows.view.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProdutoRequest {

    @NotBlank(message = "O nome do produto deve ser preenchido com caracteres válidos")
    @NotEmpty(message = "O nome do produto deve ser preenchido, não pode estar vazio")
    @Size(min=4, message = "O nome do produto deve ser preenchido com pelo menos quatro letras")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

