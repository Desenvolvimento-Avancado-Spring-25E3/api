package br.edu.infnet.elberthapi;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthapi.model.domain.LocalidadePedido;
import br.edu.infnet.elberthapi.model.service.PedidoService;

@Component
public class PedidoLoader implements ApplicationRunner  {
	
	private final PedidoService pedidoService;

	public PedidoLoader(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		LocalidadePedido localidadePedido = pedidoService.obterLocalidade("24415530");
		
		System.out.println("#API# " + localidadePedido.getOutrosMunicipiosNaUF());
	}

}
