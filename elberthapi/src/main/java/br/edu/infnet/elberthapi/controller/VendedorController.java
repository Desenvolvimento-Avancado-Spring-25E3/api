package br.edu.infnet.elberthapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Vendedor> obterLista(){
		return vendedorService.obterLista();
	}
	
	@GetMapping(value = "/{id}")
	public Vendedor obterPorId(@PathVariable Integer id) {
		return vendedorService.obterPorId(id);
	}	
}