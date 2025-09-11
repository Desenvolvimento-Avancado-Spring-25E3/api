package br.edu.infnet.elberthapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.elberthapi.model.domain.LocalidadePedido;
import br.edu.infnet.elberthapi.model.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	private final PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping("/{cep}")
	public ResponseEntity<LocalidadePedido> obterLocalidade(@PathVariable String cep) {
		
		LocalidadePedido localidadePedido = pedidoService.obterLocalidade(cep);
		
		return ResponseEntity.ok(localidadePedido);
	}
}