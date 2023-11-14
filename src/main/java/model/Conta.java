package model;

import java.util.ArrayList;
import java.util.List;

import enums.TipoOperacao.TipoOperacaoEnum;

public class Conta{
	@Override
	public String toString() {
		return "Conta [saldo=" + saldo + ", operacoes=" + operacoes + ", id=" + id + "]";
	}

	private double saldo;
	private List<Operacao> operacoes; 
	private int id;
	private double receita;
	private double despesa;

	public Conta(double saldo, ArrayList<Operacao> arrayList, int id) {
		this.saldo = saldo;
		this.operacoes = arrayList;
		this.setId(id);
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<Operacao> getOperacoes() {
		return this.operacoes;
	}

	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}
	
	public void realizarOperacao(Operacao operacao) {
		this.operacoes.add(operacao);
		if(operacao.getTipoOperacao() == TipoOperacaoEnum.DEPOSIT) {
			this.saldo += operacao.getValor();
		}
		if(operacao.getTipoOperacao() == TipoOperacaoEnum.WITHDRAW) {
			this.saldo -= operacao.getValor();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getReceita() {
		return receita;
	}

	public void setReceita(double receita) {
		this.receita = receita;
	}

	public double getDespesa() {
		return despesa;
	}

	public void setDespesa(double despesa) {
		this.despesa = despesa;
	}

}
