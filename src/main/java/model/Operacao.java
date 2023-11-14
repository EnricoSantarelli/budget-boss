package model;

import java.sql.Date;

import enums.TipoOperacao.TipoOperacaoEnum;

public class Operacao {
	private int id;
	private double valor;
	private TipoOperacaoEnum tipoOperacao;
	private String descricao;
	private Date date;

	public Operacao(double valor, TipoOperacaoEnum tipoOperacao, String descricao, Date date, int id) {
		this.valor = valor;
		this.tipoOperacao = tipoOperacao;
		this.descricao = descricao;
		this.date = date;
		this.id = id;
	}

	public double getValor() {
		return this.valor;
	}

	@Override
	public String toString() {
		return "Operacao [id=" + id + ", valor=" + valor + ", tipoOperacao=" + tipoOperacao + ", descricao=" + descricao
				+ ", date=" + date + "]";
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoOperacaoEnum getTipoOperacao() {
		return this.tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
