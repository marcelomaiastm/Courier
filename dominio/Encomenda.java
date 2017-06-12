package br.com.courier.dominio;

import java.util.Date;

public class Encomenda {
	private int idEnco;
	private Cliente clienteR;
	private Cliente clienteD;
	private Date dataEntrada;
	private Date dataSaida;
	private String descr;
	private String frete;
	private boolean statPgto;
	private int idClienteR;
	private int idClienteD;
	
	
	
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
	
	public Encomenda(Date dataEntrada, Date dataSaida, String descr,
			String frete, boolean statPgto, int idClienteR, int idClienteD) {
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.descr = descr;
		this.frete = frete;
		this.statPgto = statPgto;
		this.idClienteR = idClienteR;
		this.idClienteD = idClienteD;
	}
	
	
	public Encomenda(int idEnco, Cliente clienteR, Cliente clienteD,
			Date dataEntrada, Date dataSaida, String descr, String frete,
			boolean statPgto, int idClienteR, int idClienteD) {
		this.idEnco = idEnco;
		this.clienteR = clienteR;
		this.clienteD = clienteD;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.descr = descr;
		this.frete = frete;
		this.statPgto = statPgto;
		this.idClienteR = idClienteR;
		this.idClienteD = idClienteD;
	}


	//getters e setters
	public int getId() {
		return idEnco;
	}


	public void setId(int id) {
		this.idEnco = id;
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


	public int getIdClienteR() {
		return idClienteR;
	}


	public void setIdClienteR(int idClienteR) {
		this.idClienteR = idClienteR;
	}


	public int getIdClienteD() {
		return idClienteD;
	}


	public void setIdClienteD(int idClienteD) {
		this.idClienteD = idClienteD;
	}


	@Override
	public String toString() {
		return "Encomenda [id=" + idEnco + ", clienteR=" + clienteR + ", clienteD="
				+ clienteD + ", dataEntrada=" + dataEntrada + ", dataSaida="
				+ dataSaida + ", descr=" + descr + ", frete=" + frete
				+ ", statPgto=" + statPgto + ", idClienteR=" + idClienteR
				+ ", idClienteD=" + idClienteD + "]";
	}


		
}
