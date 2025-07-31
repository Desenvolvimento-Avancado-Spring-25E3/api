package br.edu.infnet.elberthapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.model.domain.Endereco;
import br.edu.infnet.elberthapi.model.domain.Vendedor;

@Service
public class VendedorService implements CrudService<Vendedor, Integer> {

	private final Map<Integer, Vendedor> mapa = new ConcurrentHashMap<Integer, Vendedor>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public Vendedor salvar(Vendedor vendedor) {
		
		vendedor.setId(nextId.getAndIncrement());
		
		mapa.put(vendedor.getId(), vendedor);
		
		return vendedor;
	}

	@Override
	public Vendedor obter() {
		Endereco endereco = new Endereco();			
		endereco.setCep("12345678");
		endereco.setLocalidade("Rio de Janeiro");

		Vendedor vendedor = new Vendedor();				
		vendedor.setNome("Elberth Moraes");
		vendedor.setMatricula(123);
		vendedor.setSalario(999);
		vendedor.setEhAtivo(true);
		
		vendedor.setEndereco(endereco);

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
} 