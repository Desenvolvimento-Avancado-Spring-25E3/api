package br.edu.infnet.mono.model.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.infnet.mono.model.clients.ViaCepClient;
import br.edu.infnet.mono.model.domain.Endereco;
import br.edu.infnet.mono.model.domain.Vendedor;
import br.edu.infnet.mono.model.dto.VendedorRequestDTO;
import br.edu.infnet.mono.model.dto.VendedorResponseDTO;
import br.edu.infnet.mono.model.repository.VendedorRepository;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final ViaCepClient viaCepClient;
    
    public VendedorService(VendedorRepository vendedorRepository, ViaCepClient viaCepClient) {
		this.vendedorRepository = vendedorRepository;
		this.viaCepClient = viaCepClient;
	}

    public VendedorResponseDTO incluir(VendedorRequestDTO vendedorRequestDTO) {

        vendedorRepository.findByCpf(vendedorRequestDTO.getCpf())
                .ifPresent(v -> { throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado."); });
        vendedorRepository.findByMatricula(vendedorRequestDTO.getMatricula())
                .ifPresent(v -> { throw new ResponseStatusException(HttpStatus.CONFLICT, "Matrícula já cadastrada."); });

        String cepLimpo = vendedorRequestDTO.getCep().replace("-", "");
        ViaCepClient.ViaCepResponse viaCepResponse = viaCepClient.buscarEnderecoPorCep(cepLimpo);

        if (viaCepResponse == null || viaCepResponse.isErro()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
        }

        Endereco endereco = new Endereco();
        endereco.copyFromViaCepResponse(viaCepResponse);
        
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(vendedorRequestDTO.getNome());
        vendedor.setCpf(vendedorRequestDTO.getCpf());
        vendedor.setEmail(vendedorRequestDTO.getEmail());
        vendedor.setTelefone(vendedorRequestDTO.getTelefone());
        vendedor.setMatricula(vendedorRequestDTO.getMatricula());
        vendedor.setSalario(vendedorRequestDTO.getSalario());
        vendedor.setCepInput(vendedorRequestDTO.getCep());
        vendedor.setEndereco(endereco);

        Vendedor novoVendedor = vendedorRepository.save(vendedor);

        return new VendedorResponseDTO(novoVendedor);
    }

    public List<Vendedor> obterLista() {
        return vendedorRepository.findAll();
    }

    public Vendedor obterPorId(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado."));
    }

    public Vendedor alterar(Long id, Vendedor vendedorAtualizado) {
        Vendedor vendedorExistente = vendedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado."));

        if (!vendedorExistente.getCpf().equals(vendedorAtualizado.getCpf())) {
            vendedorRepository.findByCpf(vendedorAtualizado.getCpf())
                    .ifPresent(v -> { throw new ResponseStatusException(HttpStatus.CONFLICT, "Novo CPF já cadastrado para outro vendedor."); });
            vendedorExistente.setCpf(vendedorAtualizado.getCpf());
        }
        if (!vendedorExistente.getMatricula().equals(vendedorAtualizado.getMatricula())) {
            vendedorRepository.findByMatricula(vendedorAtualizado.getMatricula())
                    .ifPresent(v -> { throw new ResponseStatusException(HttpStatus.CONFLICT, "Nova Matrícula já cadastrada para outro vendedor."); });
            vendedorExistente.setMatricula(vendedorAtualizado.getMatricula());
        }

        vendedorExistente.setNome(vendedorAtualizado.getNome());
        vendedorExistente.setEmail(vendedorAtualizado.getEmail());
        vendedorExistente.setTelefone(vendedorAtualizado.getTelefone());
        vendedorExistente.setSalario(vendedorAtualizado.getSalario());

        if (!vendedorExistente.getEndereco().getCep().equals(vendedorAtualizado.getCepInput().replace("-", ""))) {
            String cepLimpo = vendedorAtualizado.getCepInput().replace("-", "");
            ViaCepClient.ViaCepResponse viaCepResponse = viaCepClient.buscarEnderecoPorCep(cepLimpo);
            if (viaCepResponse == null || viaCepResponse.isErro()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
            }
            vendedorExistente.getEndereco().copyFromViaCepResponse(viaCepResponse);
        }

        return vendedorRepository.save(vendedorExistente);
    }

    public void excluir(Long id) {
        if (!vendedorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado.");
        }
        vendedorRepository.deleteById(id);
    }

    public void desativar(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado."));
        vendedor.setAtivo(false);
        vendedorRepository.save(vendedor);
    }

    public void ativar(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não encontrado."));
        vendedor.setAtivo(true);
        vendedorRepository.save(vendedor);
    }
}