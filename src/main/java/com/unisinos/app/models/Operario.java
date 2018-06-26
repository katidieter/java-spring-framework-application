//ConcreteHandler
package com.unisinos.app.models;

public class Operario implements Setor{
	private Setor proximoSetor = null;
	private String responsabilidade = "Operário";

	@Override
	public void adicionaSetor(Setor setor) {
		this.proximoSetor = setor;
	}

	@Override
	public Setor enviarParaSetor(String mensagem) {
		if (mensagem.equals(responsabilidade)) {
			System.out.println("Enviado para setor " + this.responsabilidade);
			return this;
		} else {
			if(proximoSetor != null) {
				proximoSetor.enviarParaSetor(mensagem);
				return proximoSetor;
			}
		}
		return null;	
	}

	@Override
	public Setor getSetor() {
		return this.proximoSetor;
	}

	@Override
	public String getResponsabilidade() {
		return this.responsabilidade;
	}
}
