package br.edu.infnet.elberthapi.model.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.elberthapi.model.domain.LocalidadePedido;

@FeignClient(name = "elberthpedidoapi", url = "${elberthpedidoapi.url}")
public interface PedidoFeignClient {

	@GetMapping("/api/localidades/{cep}")
	LocalidadePedido obterLocalidadePorCep(@PathVariable String cep);
}