package br.edu.infnet.elberthapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.domain.exceptions.VendedorInvalidoException;
import br.edu.infnet.elberthapi.model.domain.exceptions.VendedorNaoEncontradoException;

@Service
public class VendedorService implements CrudService<Vendedor, Integer> {

	private final Map<Integer, Vendedor> mapa = new ConcurrentHashMap<Integer, Vendedor>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	private void validar(Vendedor vendedor) {
		if(vendedor == null) {
			throw new IllegalArgumentException("O vendedor não pode estar nulo!");
		}

		if(vendedor.getNome() == null || vendedor.getNome().trim().isEmpty()) {
			throw new VendedorInvalidoException("O nome do vendedor é uma informação obrigatória!");
		}		
	}
	
	@Override
	public Vendedor incluir(Vendedor vendedor) {
		
		validar(vendedor);
		
		if(vendedor.getId() != null && vendedor.getId() != 0) {
			throw new IllegalArgumentException("Um novo vendedor não pode ter um ID na inclusão!");			
		}
		
		vendedor.setId(nextId.getAndIncrement());		
		
		mapa.put(vendedor.getId(), vendedor);
		
		return vendedor;
	}

	@Override
	public Vendedor alterar(Integer id, Vendedor vendedor) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");			
		}
		
		validar(vendedor);
		
		obterPorId(id);
		
		vendedor.setId(id);

		mapa.put(vendedor.getId(), vendedor);
		
		return vendedor;
	}

	@Override
	public void excluir(Integer id) {
		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");			
		}
		
		if(!mapa.containsKey(id)) {
			throw new VendedorNaoEncontradoException("O vendedor com ID " + id + " não foi encontrado!");
		}

		mapa.remove(id);
	}

	public Vendedor inativar(Integer id) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para inativação não pode ser nulo/zero!");			
		}
		
		Vendedor vendedor = obterPorId(id);
		
		if(!vendedor.isEhAtivo()) {
			System.out.println("Vendedor " + vendedor.getNome() + " já está inativo!");
			return vendedor;
		}
		
		vendedor.setEhAtivo(false);
		
		mapa.put(vendedor.getId(), vendedor);
		
		return vendedor;
	}
	
	@Override
	public List<Vendedor> obterLista() {
		
		return new ArrayList<Vendedor>(mapa.values());
	}

	@Override
	public Vendedor obterPorId(Integer id) {

		Vendedor vendedor = mapa.get(id);
		
		if(vendedor == null) {
			throw new IllegalArgumentException("Imposível obter o vendedor pelo ID " + id);
		}
		
		return vendedor;
	}
} 