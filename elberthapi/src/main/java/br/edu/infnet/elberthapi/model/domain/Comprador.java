package br.edu.infnet.elberthapi.model.domain;

public class Comprador extends Pessoa {

	private String fidelidade;

	//TODO construtor do comprador
	
	//TODO toString do comprador
	
	@Override
	public String obterTipo() {
		return "Comprador";
	}

	public String getFidelidade() {
		return fidelidade;
	}

	public void setFidelidade(String fidelidade) {
		this.fidelidade = fidelidade;
	}

}