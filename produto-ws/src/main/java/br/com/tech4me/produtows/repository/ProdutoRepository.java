package br.com.tech4me.produtows.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.produtows.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>{
    
}
