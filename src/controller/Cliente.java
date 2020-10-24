package controller;

public class Cliente {
	private String name;
	private long cpf;
	private String endereco;
	private long telefone;
	public Cliente(String nome, String endereco, long cpf, long telefone) {
		this.name = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
				
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public double getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	
}
