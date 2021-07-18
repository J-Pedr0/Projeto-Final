package br.com.tech4me.produtows.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.produtows.integration.VendaFeignClient;
import br.com.tech4me.produtows.model.Produto;
import br.com.tech4me.produtows.repository.ProdutoRepository;
import br.com.tech4me.produtows.shared.ProdutoDto;

@Service
public class ProdutoServiceImp implements ProdutoService{
    @Autowired
    private ProdutoRepository repositorio;

    @Autowired
    private VendaFeignClient vendaMs;


    @Override
    public ProdutoDto adicionarProdutos(ProdutoDto produto) {
        ModelMapper mapa = new ModelMapper();
        Produto prod = mapa.map(produto, Produto.class);
        prod = repositorio.save(prod);
        return mapa.map(prod, ProdutoDto.class);
    }

    @Override
    public void removerProduto(String id) {
        repositorio.deleteById(id);
        
    }

    @Override
    public ProdutoDto atualizarProduto(String id, ProdutoDto produto) {
        ModelMapper mapa = new ModelMapper();
        Produto prod = mapa.map(produto, Produto.class);
        prod.setId(id);
        return mapa.map(prod, ProdutoDto.class);
    }

    @Override
    public List<ProdutoDto> obterTodos() {
        List<Produto> produtos = repositorio.findAll();
        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<ProdutoDto> obterPorId(String id) {
        Optional<Produto> prod = repositorio.findById(id);
        if (prod.isPresent()) {
            ProdutoDto prodDto = new ModelMapper().map(prod.get(), ProdutoDto.class);
            prodDto.setVenda(vendaMs.obterPorProduto(id));
            return Optional.of(prodDto);
        }
        return Optional.empty();
    }
    
}
