package br.edu.infnet.mono.dto;

import br.edu.infnet.mono.model.domain.Vendedor;

public class VendedorResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Integer matricula;
    private Double salario;
    private boolean ativo = true;
    private EnderecoResponseDTO endereco;
    
    public VendedorResponseDTO(Vendedor vendedor) {
		this.setId(vendedor.getId());
		this.setNome(vendedor.getNome());
		this.setCpf(vendedor.getCpf());
		this.setEmail(vendedor.getEmail());
		this.setTelefone(vendedor.getTelefone());
		this.setMatricula(vendedor.getMatricula());
		this.setSalario(vendedor.getSalario());
		this.setAtivo(vendedor.isAtivo());
		this.setEndereco(new EnderecoResponseDTO(vendedor.getEndereco()));
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public EnderecoResponseDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoResponseDTO endereco) {
		this.endereco = endereco;
	}
}