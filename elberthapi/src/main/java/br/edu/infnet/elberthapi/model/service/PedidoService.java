package br.edu.infnet.elberthapi.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.model.clients.PedidoFeignClient;
import br.edu.infnet.elberthapi.model.domain.LocalidadePedido;

@Service
public class PedidoService {

	private final PedidoFeignClient pedidoFeignClient;
	
	public PedidoService(PedidoFeignClient pedidoFeignClient) {
		this.pedidoFeignClient= pedidoFeignClient;
	}

	public LocalidadePedido obterLocalidade(String cep) {
		return pedidoFeignClient.obterLocalidadePorCep(cep);
	}
}