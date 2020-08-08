package controller;

import java.sql.Date;

public class Servicos {
	private TipoServico tipoServico;
	private Date dataInicio;
	private Date dataFinal;
	private double preco;
    private Cliente cliente;
    public Servicos(TipoServico tipoServico, double preco, Cliente cliente) {
    	this.tipoServico = tipoServico;
    	this.preco = preco;
    	this.cliente = cliente;
    }
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
