package br.com.tech4me.produtows.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tech4me.produtows.shared.Venda;

@FeignClient(name = "venda-ms")
public interface VendaFeignClient {
    @GetMapping(path = "/api/venda/{produtoVendido}/lista")
    List<Venda> obterPorProduto(@PathVariable String produtoVendido);
}
