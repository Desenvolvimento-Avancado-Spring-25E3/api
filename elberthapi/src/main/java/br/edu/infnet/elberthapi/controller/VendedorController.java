package br.edu.infnet.elberthapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

	private final VendedorService vendedorService;
	
	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}
	
	@GetMapping
	public Vendedor obterVendedor() {
		
		return vendedorService.obter();
	}
}