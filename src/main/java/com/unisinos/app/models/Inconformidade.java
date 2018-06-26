package com.unisinos.app.models;

import ch.qos.logback.core.net.SyslogOutputStream;

//ConcreteObserver
public class Inconformidade implements ITipoAcao {
		
	private int codigo;
	private String obra;
	private String descricao;
	private String situacao;
	private Acao acao;
	
	public Inconformidade(String obra, String descricao, Acao acao) {
		this.codigo = 1 + (int)(Math.random() * 1000000000);
		this.obra = obra;
		this.descricao = descricao;
		this.situacao = "Pendente";
		this.acao = acao;
	}
	
	@Override
	public void atualiza(IAcao a) {
		this.situacao = a.getSituacao();
		System.out.println("----- Inconformidade teve situação atualizada para " + situacao);
	}
	
	public void mudar(String situacao) {
		this.acao.setSitacao(situacao);
	}
	
	public void notificarAcao() {
		this.acao.enviarNotificacao();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public int getCodigo() {
		return this.codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getObra() {
		return this.obra;
	}
	public void adicionaAcao(Acao acao) {
		this.acao = acao;
	}
}