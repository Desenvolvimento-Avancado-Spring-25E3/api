package br.edu.infnet.mono;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.mono.dto.VendedorRequestDTO;
import br.edu.infnet.mono.model.domain.Comentario;
import br.edu.infnet.mono.model.service.ComentarioService;
import br.edu.infnet.mono.model.service.VendedorService;

@Component
public class VendedorLoader implements ApplicationRunner {
	
	private final VendedorService vendedorService;
	private final ComentarioService comentarioService;
	
	public VendedorLoader(VendedorService vendedorService, ComentarioService comentarioService) {
		this.vendedorService = vendedorService;
		this.comentarioService = comentarioService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("vendedores.txt");

		if (inputStream == null) {
		    throw new FileNotFoundException("Arquivo vendedores.txt não encontrado no resources!");
		}
		
		for(VendedorRequestDTO vendedor : processarArquivoVendedores(inputStream)) {
			System.out.println("# " + vendedor.getNome());
		}
	}
	
    public List<VendedorRequestDTO> processarArquivoVendedores(InputStream inputStream) {
        List<VendedorRequestDTO> processedVendedores = new ArrayList<>();
        try (BufferedReader leitura = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String linha;
            int lineNumber = 0;
            while ((linha = leitura.readLine()) != null) {
                lineNumber++;

                try {
                    VendedorRequestDTO dto = parseToVendedorDTO(linha);
                    vendedorService.incluir(dto);
                    
                    comentarioService.incluir(new Comentario(dto.getNome(), dto.getEmail()));
                    
                    processedVendedores.add(dto);
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha " +lineNumber+"["+linha+"]");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo de vendedores: " + e.getMessage());
            throw new RuntimeException("Falha ao processar o arquivo de vendedores.", e);
        }
        return processedVendedores;
    }
	
    private VendedorRequestDTO parseToVendedorDTO(String linha) {
        String[] campos = linha.split(";");
        if (campos.length != 7) {
            throw new IllegalArgumentException("Formato de linha inválido. Esperado 7 campos separados por ';'.");
        }

        VendedorRequestDTO dto = new VendedorRequestDTO();
        dto.setNome(campos[0].trim());
        dto.setCpf(campos[1].trim());
        dto.setEmail(campos[2].trim());
        dto.setTelefone(campos[3].trim());
        dto.setMatricula(Integer.valueOf(campos[4].trim()));
        dto.setSalario(Double.valueOf(campos[5].trim()));
        dto.setCep(campos[6].trim());
        
        return dto;
    }
}
