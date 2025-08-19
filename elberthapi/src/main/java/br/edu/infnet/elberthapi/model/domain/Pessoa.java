package br.edu.infnet.elberthapi.model.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 3, max = 50, message = "O nome deve estar entre 3 e 50 caracteres.")
	private String nome;
	
	@NotBlank(message = "O e-mail é obrigatório.")
	@Email(message = "O e-mail está inválido.")
	private String email;
	
	//"XXX.XXX.XXX-XX"
	//TODO @Pattern(regexp
	private String cpf;
	
	//(XX) XXXXX-XXXX OU (XX) XXXX-XXXX
	private String telefone;
	
	//TODO Criação do construtor de pessoa com nome, email, cpf e telefone
	
	@Override
	public String toString() {

		return String.format("id=%d, nome=%s, email=%s, cpf=%s, telefone=%s",
                id, nome, email, cpf, telefone);
	}
	
	public abstract String obterTipo();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}