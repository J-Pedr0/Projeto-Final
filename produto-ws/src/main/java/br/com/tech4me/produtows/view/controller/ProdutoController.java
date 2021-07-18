package br.com.tech4me.produtows.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.produtows.service.ProdutoService;
import br.com.tech4me.produtows.shared.ProdutoDto;
import br.com.tech4me.produtows.view.model.ProdutoRequest;
import br.com.tech4me.produtows.view.model.ProdutoResponse;
import br.com.tech4me.produtows.view.model.ProdutoResponseDetalhes;


@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    @Autowired
    ProdutoService servico;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodosOsProdutos() {
        ModelMapper mapa = new ModelMapper();
        List<ProdutoDto> prodDto = servico.obterTodos();
        List<ProdutoResponse> prodResponse = prodDto.stream()
        .map(prod -> mapa.map(prod, ProdutoResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(prodResponse, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponseDetalhes> obterUmProduto(@PathVariable String id) {
        Optional<ProdutoDto> prod = servico.obterPorId(id);
        if (prod.isPresent()) {
            return new ResponseEntity<>(new ModelMapper()
            .map(prod.get(), ProdutoResponseDetalhes.class)
            , HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/status")
    public String status (@Value("${local.server.port}") String porta) {
        return String.format("O serviço está rodando na porta %s", porta);
    }
    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionarUmProduto(@RequestBody @Valid ProdutoRequest produto) {
        ModelMapper mapa = new ModelMapper();
        ProdutoDto prodDto = mapa.map(produto, ProdutoDto.class);
        prodDto = servico.adicionarProdutos(prodDto);
        return new ResponseEntity<>(mapa.map(prodDto, ProdutoResponse.class), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerProd(@PathVariable String id) {
        servico.removerProduto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable String id, @RequestBody @Valid ProdutoRequest produto) {
        ModelMapper mapa = new ModelMapper();
        ProdutoDto prodDto = mapa.map(produto, ProdutoDto.class);
        prodDto = servico.atualizarProduto(id, prodDto);
        return new ResponseEntity<>(mapa.map(prodDto, ProdutoResponse.class), HttpStatus.OK);
    }
}
