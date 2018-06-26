//ConcreteObserver
package com.unisinos.app.models;

import java.util.ArrayList;
import java.util.List;

public class ImpactoAmbiental implements ITipoAcao {
	
	private int codigo;
	private String obra;
	private String descricao;
	private String analise;
	private String grupoDeImpacto;
	private String data;
	private String situacao;
	private Acao acao;

	public ImpactoAmbiental(String obra, String descricao, String analise, String grupoImpacto, String data, Acao acao) {
		this.codigo =  1 + (int)(Math.random() * 1000000000);
		this.obra = obra;
		this.grupoDeImpacto = grupoImpacto;
		this.descricao = descricao;
		this.analise = analise;
		this.data = data;
		this.situacao = "Pendente";
		this.acao = acao;
	}
	
	@Override
	public void atualiza(IAcao a) {
		this.situacao = a.getSituacao();
		System.out.println("----- Impacto ambiental atualizado para situacao" + this.situacao);
	}
	
	public void mudar(String situacao) {
		this.acao.setSitacao(situacao);
	}
		
	public void notificarAcao() {
		this.acao.enviarNotificacao();
	}

	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	public String getAnalise() {
		return this.analise;
	}
	
	public String getGrupoDeImpacto() {
		return this.grupoDeImpacto;
	}
	public String getData() {
		return this.data;
	}
	public String getSituacao() {
		return this.situacao;
	}
	public String getObra() {
		return this.obra;
	}
	
	public Acao getAcao() {
		return this.acao;
	}
	public void adicionaAcao(Acao acao) {
		this.acao = acao;
	}
	public void realizarAcao(String situacao) {
		mudar(situacao);
		notificarAcao();
	}
}
