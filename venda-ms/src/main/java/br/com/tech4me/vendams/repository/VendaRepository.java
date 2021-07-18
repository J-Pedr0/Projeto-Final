package br.com.tech4me.vendams.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tech4me.vendams.model.Venda;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface VendaRepository extends MongoRepository<Venda, String> {
    
    List<Venda> findByProdutoVendido(String produtoVendido);
}
