package br.edu.infnet.elberthapi;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthapi.model.domain.Endereco;
import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@Component
public class VendedorLoader implements ApplicationRunner {
	
	private final VendedorService vendedorService;
	
	public VendedorLoader(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("vendedor.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();

		String[] campos = null;
		
		while(linha != null) {
			
			campos = linha.split(";");
			
			Endereco endereco = new Endereco();			
			endereco.setCep(campos[7]);
			endereco.setLocalidade(campos[8]);
			
			Vendedor vendedor = new Vendedor();
			vendedor.setNome(campos[0]);
			vendedor.setMatricula(Integer.valueOf(campos[1]));
			vendedor.setSalario(Double.valueOf(campos[2]));
			vendedor.setEhAtivo(Boolean.valueOf(campos[3]));
			vendedor.setCpf(campos[4]);
			vendedor.setEmail(campos[5]);
			vendedor.setTelefone(campos[6]);
			
			vendedor.setEndereco(endereco);
			
			vendedorService.salvar(vendedor);

			//TODO Imprimir os vendedores após a leitura do arquivo
			System.out.println(vendedor);
			
			linha = leitura.readLine();
		}
		
		System.out.println("- " + vendedorService.obterLista().size());

		leitura.close();
	}
}
