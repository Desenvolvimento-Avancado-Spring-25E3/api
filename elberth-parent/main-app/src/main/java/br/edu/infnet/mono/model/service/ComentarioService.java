package br.edu.infnet.mono.model.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.infnet.mono.model.domain.Comentario;
import br.edu.infnet.mono.model.repository.ComentarioRepository;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
		this.comentarioRepository = comentarioRepository;
	}
    
    public Comentario incluir(Comentario comentario) {
    		return comentarioRepository.save(comentario);
    }
    
    public List<Comentario> obterLista() {
        return comentarioRepository.findAll();
    }

    public void excluir(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrado.");
        }
        comentarioRepository.deleteById(id);
    }
}