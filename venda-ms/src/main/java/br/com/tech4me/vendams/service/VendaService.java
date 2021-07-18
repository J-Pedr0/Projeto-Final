package br.com.tech4me.vendams.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.vendams.shared.VendaDto;

public interface VendaService {
    VendaDto fazerVenda(VendaDto venda);
    void removerVenda(String id);
    Optional<VendaDto> obterVendaPorId(String id);
    List<VendaDto> obterTodasVendas();
    List<VendaDto> obterPorProduto(String produtoVendido);
    VendaDto atualizarVenda(String id, VendaDto venda);
    boolean conferirSeTemNoEstoque(String id);
}
