package br.com.tech4me.vendams.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.vendams.model.Venda;
import br.com.tech4me.vendams.repository.VendaRepository;
import br.com.tech4me.vendams.shared.VendaDto;

@Service
public class VendaSeviceImp implements VendaService {
    @Autowired
    private VendaRepository repositorio;

    @Override
    public VendaDto fazerVenda(VendaDto venda) {
        return salvarVenda(venda);
    }

    private VendaDto salvarVenda(VendaDto venda) {
        ModelMapper mapa = new ModelMapper();
        Venda vendaEntity = mapa.map(venda, Venda.class);
        vendaEntity = repositorio.save(vendaEntity);
        return mapa.map(vendaEntity, VendaDto.class);
    }

    @Override
    public void removerVenda(String id) {
        repositorio.deleteById(id);
    }

    @Override
    public Optional<VendaDto> obterVendaPorId(String id) {
        Optional<Venda> venda = repositorio.findById(id);
        if(venda.isPresent()) {
            return Optional.of(new ModelMapper()
            .map(venda.get(), VendaDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<VendaDto> obterTodasVendas() {
        List<Venda> vendas = repositorio.findAll();
        return vendas.stream()
        .map(venda -> new ModelMapper()
        .map(venda, VendaDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public List<VendaDto> obterPorProduto(String produtoVendido) {
        List<Venda> vendas= repositorio.findByProdutoVendido(produtoVendido);
        return vendas.stream()
        .map(venda -> new ModelMapper()
        .map(venda, VendaDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public VendaDto atualizarVenda(String id, VendaDto venda) {
        venda.setId(id);
        return salvarVenda(venda);
    }

    @Override
    public boolean conferirSeTemNoEstoque(String id) {
        Optional<Venda> venda = repositorio.findById(id);
        if(venda.isPresent()) {
            venda.get().setTemEmEstoque(false);
            repositorio.save(venda.get());
            return true;
        }
        return false;
    }
    
}
