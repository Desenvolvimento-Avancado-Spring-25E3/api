package br.edu.infnet.elberthapi.model.service;

import java.util.List;

//TODO Atualizar com as funcionalidades de inclusão, de alteração e de obter por id; retirar o salvar
public interface CrudService<T,ID> {

	T salvar(T entity);
	T obterPorId(ID id);
	void excluir(ID id);
	List<T> obterLista();
}
