package com.unisinos.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unisinos.app.models.Acao;
import com.unisinos.app.models.Engenharia;
import com.unisinos.app.models.GestaoAmbiental;
import com.unisinos.app.models.ImpactoAmbiental;
import com.unisinos.app.models.Inconformidade;
import com.unisinos.app.models.Operario;
import com.unisinos.app.models.Setor;

@Service
public class InconformidadeService {
	private Setor operario = new Operario();
	private Setor engenharia = new Engenharia();
	private Setor gestaoAmbiental = new GestaoAmbiental();
	
	private Acao acaoPadrao = new Acao("Realizar plano de emergência 9000.", engenharia);
	private List<Inconformidade> inconformidades = new ArrayList<>( Arrays.asList(
			new Inconformidade("Obra001", "Operários sem capacete de segurança.", acaoPadrao)
			));

	public void adicionar(Inconformidade inconformidade) {
		inconformidades.add(inconformidade);
		Acao acao = new Acao("Realizar plano de emergência 255.", engenharia);
		System.out.println(engenharia.getResponsabilidade());
		inconformidade.adicionaAcao(acao);
		acao.anexar(inconformidade);
	}

	public List<Inconformidade> buscarInconformidades() {
		return this.inconformidades;
	}	
	
	public Inconformidade getInconformidade(int codigo) {
		return inconformidades.stream().filter(a -> a.getCodigo() == codigo).findFirst().get();
	}
	
	public void proximoSetor(Inconformidade inconformidade) {
		configurarCadeia();
		Acao acao = inconformidade.getAcao();
		Setor setorAtual = acao.getSetor();
		if(setorAtual.getResponsabilidade().equals(engenharia.getResponsabilidade())) {
			acao.setSetor(setorAtual.enviarParaSetor(operario.getResponsabilidade()));
		}
		else {
			acao.setSetor(setorAtual.enviarParaSetor(gestaoAmbiental.getResponsabilidade()));
		}
	}
	
	private void configurarCadeia() {
		engenharia.adicionaSetor(operario);
		operario.adicionaSetor(gestaoAmbiental);
	}
	
	
	public void iniciarAcao(Inconformidade inconformidade) {
		inconformidade.mudar("Em andamento");
		inconformidade.notificarAcao();
	
	}
	
	public void concluirAcao(Inconformidade inconformidade) {
		inconformidade.mudar("Concluído");
		inconformidade.notificarAcao();
	}
}

