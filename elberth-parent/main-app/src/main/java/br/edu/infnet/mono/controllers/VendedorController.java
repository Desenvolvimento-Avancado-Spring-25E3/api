package br.edu.infnet.mono.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.mono.dto.VendedorRequestDTO;
import br.edu.infnet.mono.dto.VendedorResponseDTO;
import br.edu.infnet.mono.model.domain.Vendedor;
import br.edu.infnet.mono.model.service.VendedorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;
    
    public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VendedorResponseDTO> incluir(@Valid @RequestBody VendedorRequestDTO vendedor) {
        VendedorResponseDTO novoVendedor = vendedorService.incluir(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Vendedor>> obterLista() {
        List<Vendedor> vendedores = vendedorService.obterLista();
        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Vendedor> obterPorId(@PathVariable Long id) {
        Vendedor vendedor = vendedorService.obterPorId(id);
        return ResponseEntity.ok(vendedor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Vendedor> alterar(@PathVariable Long id, @Valid @RequestBody Vendedor vendedor) {
        Vendedor vendedorAtualizado = vendedorService.alterar(id, vendedor);
        return ResponseEntity.ok(vendedorAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        vendedorService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desativar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> desativar(@PathVariable("id") Long id) {
        vendedorService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        vendedorService.ativar(id);
        return ResponseEntity.noContent().build();
    }
}