package br.edu.infnet.elberthapi.controller;

import java.util.List;

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

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

	private final VendedorService vendedorService;
	
	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}
	
	@PostMapping
	public ResponseEntity<Vendedor> incluir(@RequestBody Vendedor vendedor) {
		
		Vendedor novoVendedor = vendedorService.incluir(vendedor);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
	}
		
	@PutMapping(value = "/{id}")
	public Vendedor alterar(@PathVariable Integer id, @RequestBody Vendedor vendedor) {
		return vendedorService.alterar(id, vendedor);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		vendedorService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}/inativar")
	public Vendedor inativar(@PathVariable Integer id) {
		return vendedorService.inativar(id);
	}
	
	@GetMapping
	public ResponseEntity<List<Vendedor>> obterLista(){
		
		List<Vendedor> lista = vendedorService.obterLista();
		
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{id}")
	public Vendedor obterPorId(@PathVariable Integer id) {
		return vendedorService.obterPorId(id);
	}	
}