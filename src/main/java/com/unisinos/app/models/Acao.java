//ConcreteSubject
package com.unisinos.app.models;

import java.util.LinkedList;

public class Acao implements IAcao{
	
	private int codigo;
	private String descricao;	
	private LinkedList acoes;
	private String situacao;
	private Setor setor;
	
		
	public Acao(String descricao, Setor setor) {
		this.codigo =  1 + (int)(Math.random() * 1000000000);
		this.descricao = descricao;
		this.acoes = new LinkedList();
		this.situacao = "Pendente";
		this.setor = setor;
	}
	
	@Override
	public void anexar(ITipoAcao tipoDeAcao) {
		acoes.add(tipoDeAcao);
	}

	@Override
	public void desanexar(ITipoAcao tipoDeAcao) {
		acoes.remove(tipoDeAcao);
	}

	@Override
	public void enviarNotificacao() {
		for(int i=0; i < acoes.size(); i++) {
			((ITipoAcao)acoes.get(i)).atualiza(this);
		}
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSituacao() {
		return this.situacao;
	}
	@Override
	public void setSitacao(String situacao) {
		this.situacao = situacao;
	}
	
	public LinkedList<Acao> getAcoes() {
		return this.acoes;
	}
	public void setAcoes(LinkedList<Acao> acoes) {
		this.acoes = acoes;
	}
	public int getCodigo() {
		return this.codigo;
	}
	public Setor getSetor() {
		return this.setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}