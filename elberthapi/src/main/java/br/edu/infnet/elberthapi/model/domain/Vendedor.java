package br.edu.infnet.elberthapi.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vendedor extends Pessoa {

	@NotNull
	private int matricula;
	
	@Min(value = 0)
	private double salario;
	
	private boolean ativo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	@Valid
	private Endereco endereco;
	
	//TODO construtor padrão do vendedor para marcar o vendedor como ativo
	
	//TODO construtor com os campos de pessoa e os demais campos de vendedor
	
	@Override
	public String toString() {
		return String.format("Vendedor{%s, matricula=%d, salario=%.2f, ehAtivo=%s, %s", super.toString(), matricula, salario, ativo ? "ativo" : "inativo", endereco);
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}