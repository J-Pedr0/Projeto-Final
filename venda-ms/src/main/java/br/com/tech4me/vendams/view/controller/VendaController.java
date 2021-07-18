package br.com.tech4me.vendams.view.controller;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.vendams.service.VendaService;
import br.com.tech4me.vendams.shared.VendaDto;
import br.com.tech4me.vendams.view.model.VendaModeloAlteracao;
import br.com.tech4me.vendams.view.model.VendaModeloInclusao;
import br.com.tech4me.vendams.view.model.VendaModeloResponse;



@RestController
@RequestMapping("/api/venda")
public class VendaController {
    @Autowired
    private VendaService servico;

    @GetMapping(value = "status")
    public String StatusServico(@Value("${local.server.port") String porta) {
        return String.format("O serviço está rodando na porta %s", porta);
    }
    @PostMapping
    public ResponseEntity<VendaModeloResponse> fazerVenda(@RequestBody @Valid VendaModeloInclusao Venda) {
        ModelMapper mapa = new ModelMapper();
        VendaDto vendDto = mapa.map(Venda, VendaDto.class);
        vendDto = servico.fazerVenda(vendDto);
        return new ResponseEntity<>(mapa.map(vendDto, VendaModeloResponse.class), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<VendaModeloResponse>> obterTodasVendas() {
        List<VendaDto> vendsDto = servico.obterTodasVendas();
        if(vendsDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ModelMapper mapa = new ModelMapper();
        List<VendaModeloResponse> response = vendsDto.stream()
        .map(vendDto -> mapa.map(vendDto, VendaModeloResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "/{produtoVendido}/lista")
    public ResponseEntity<List<VendaModeloResponse>> obterPorProduto(@PathVariable String produtoVendido) {
        List<VendaDto> vendsDto = servico.obterPorProduto(produtoVendido);
        if(vendsDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ModelMapper mapa = new ModelMapper();
        List<VendaModeloResponse> response = vendsDto.stream()
        .map(vendDto -> mapa.map(vendDto, VendaModeloResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaModeloResponse> obterVendaPorId(@PathVariable String id) {
        Optional<VendaDto> Venda = servico.obterVendaPorId(id);
        if(Venda.isPresent()) {
            return new ResponseEntity<>(new ModelMapper()
            .map(Venda.get()
            , VendaModeloResponse.class)
            , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<VendaModeloResponse> atualizarVenda(@PathVariable String id
    , @Valid @RequestBody VendaModeloAlteracao Venda) {
        ModelMapper mapa = new ModelMapper();
        VendaDto vendDto = mapa.map(Venda, VendaDto.class);
        vendDto = servico.atualizarVenda(id, vendDto);
        return new ResponseEntity<>(mapa.map(vendDto, VendaModeloResponse.class), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerVenda(@PathVariable String id) {
        servico.removerVenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> conferirSeTemNoEstoque(@PathVariable String id) {
        if(servico.conferirSeTemNoEstoque(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
