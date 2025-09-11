package br.edu.infnet.elberthpedidoapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.elberthpedidoapi.model.domain.LocalidadeQueryResult;
import br.edu.infnet.elberthpedidoapi.model.service.LocalidadeService;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadeController {
	
	private final LocalidadeService localidadeService;
	
	public LocalidadeController(LocalidadeService localidadeService) {
		this.localidadeService = localidadeService;
	}

	@GetMapping("/{cep}")
	public ResponseEntity<LocalidadeQueryResult> obterPorCep(@PathVariable String cep){
		
		LocalidadeQueryResult localidadeQueryResult =  localidadeService.obterLocalidadesPorCep(cep);
		
		return ResponseEntity.ok(localidadeQueryResult);
	}
}