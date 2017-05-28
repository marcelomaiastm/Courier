package br.com.courier.dominio;

public class Cliente implements Comparable<Cliente>{
	private int id;
	private String nome;
	private String cpf;
	
	
	//construtores
	
	public Cliente() {
	}


	public Cliente(int id) {
		this.id = id;
	}

	public Cliente(int id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	
	//getters e setters

	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}


	@Override
	public int compareTo(Cliente cliente) {
		return this.cpf.compareTo(cliente.getCpf());
	}
	
	
}
