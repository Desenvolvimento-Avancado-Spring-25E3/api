package br.edu.infnet.elberthapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.domain.exceptions.VendedorInvalidoException;

@Service
public class VendedorService implements CrudService<Vendedor, Integer> {

	private final Map<Integer, Vendedor> mapa = new ConcurrentHashMap<Integer, Vendedor>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	//TODO transformar o salvar em incluir e alterar
	//TODO criar exceptions para validar vendedores inválidos e vendedores não encontrados
	@Override
	public Vendedor salvar(Vendedor vendedor) {
		
		if(vendedor.getNome() == null) {
			throw new VendedorInvalidoException("O nome do vendedor é uma informação obrigatória!");
		}
		
		vendedor.setId(nextId.getAndIncrement());		
		mapa.put(vendedor.getId(), vendedor);
		
		return vendedor;
	}

	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
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