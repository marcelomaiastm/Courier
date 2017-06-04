package br.com.courier.dominio;

import java.util.Date;

public class Encomenda {
	private int id;
	private Cliente clienteR;
	private Cliente clienteD;
	private Date dataEntrada;
	private Date dataSaida;
	private String descr;
	private String frete;
	private boolean statPgto;
	
	
	
	//construtores
	public Encomenda() {
	}


	public Encomenda(Cliente clienteR, Cliente clienteD, Date dataEntrada,
			Date dataSaida, String descr, String frete, boolean statPgto) {
		this.clienteR = clienteR;
		this.clienteD = clienteD;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.descr = descr;
		this.frete = frete;
		this.statPgto = statPgto;
		
	}


	public Encomenda(int id, Cliente clienteR, Cliente clienteD,
			Date dataEntrada, Date dataSaida, String descr, String frete,
			boolean statPgto, String encoStatus) {
		this.id = id;
		this.clienteR = clienteR;
		this.clienteD = clienteD;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.descr = descr;
		this.frete = frete;
		this.statPgto = statPgto;
		
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Cliente getClienteR() {
		return clienteR;
	}


	public void setClienteR(Cliente clienteR) {
		this.clienteR = clienteR;
	}


	public Cliente getClienteD() {
		return clienteD;
	}


	public void setClienteD(Cliente clienteD) {
		this.clienteD = clienteD;
	}


	public Date getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public Date getDataSaida() {
		return dataSaida;
	}


	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getFrete() {
		return frete;
	}


	public void setFrete(String frete) {
		this.frete = frete;
	}


	public boolean isStatPgto() {
		return statPgto;
	}


	public void setStatPgto(boolean statPgto) {
		this.statPgto = statPgto;
	}


	

	@Override
	public String toString() {
		return "Encomenda [id=" + id + ", clienteR=" + clienteR + ", clienteD="
				+ clienteD + ", dataEntrada=" + dataEntrada + ", dataSaida="
				+ dataSaida + ", descr=" + descr + ", frete=" + frete
				+ ", statPgto=" + statPgto +  "]";
	}


	public Encomenda(int id, Cliente clienteR, Date dataEntrada,
			Date dataSaida, String descr, String frete, boolean statPgto,
			String encoStatus) {
		this.id = id;
		this.clienteR = clienteR;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.descr = descr;
		this.frete = frete;
		this.statPgto = statPgto;
	}

}
