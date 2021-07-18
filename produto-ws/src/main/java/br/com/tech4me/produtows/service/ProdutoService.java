package br.com.tech4me.produtows.service;

import java.util.List;

import java.util.Optional;

import br.com.tech4me.produtows.shared.ProdutoDto;


public interface ProdutoService {
    ProdutoDto adicionarProdutos(ProdutoDto produto);
    void removerProduto(String id);
    ProdutoDto atualizarProduto(String id, ProdutoDto produto);
    List<ProdutoDto> obterTodos();
    Optional<ProdutoDto> obterPorId(String id);
    
}
