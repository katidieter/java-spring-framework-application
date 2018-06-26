//Handler
package com.unisinos.app.models;

public interface Setor {
	public void adicionaSetor(Setor setor);
	public Setor enviarParaSetor(String mensagem);
	public Setor getSetor();
	public String getResponsabilidade();
}

