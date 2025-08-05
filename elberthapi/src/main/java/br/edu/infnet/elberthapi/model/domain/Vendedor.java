package br.edu.infnet.elberthapi.model.domain;

public class Vendedor extends Pessoa {

	private int matricula;
	private double salario;
	private boolean ehAtivo;
	private Endereco endereco;
	
	//TODO construtor padrão do vendedor para marcar o vendedor como ativo
	
	//TODO construtor com os campos de pessoa e os demais campos de vendedor
	
	@Override
	public String toString() {

		return String.format("%s - %d - %.2f - %s - %s", 
				super.toString(), matricula, salario, ehAtivo ? "ativo" : "inativo", endereco);
	}

	@Override
	public String obterTipo() {
		return "Vendedor";
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isEhAtivo() {
		return ehAtivo;
	}

	public void setEhAtivo(boolean ehAtivo) {
		this.ehAtivo = ehAtivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}