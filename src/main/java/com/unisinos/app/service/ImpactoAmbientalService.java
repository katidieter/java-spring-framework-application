package com.unisinos.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

import com.unisinos.app.models.Acao;
import com.unisinos.app.models.Engenharia;
import com.unisinos.app.models.GestaoAmbiental;
import com.unisinos.app.models.ImpactoAmbiental;
import com.unisinos.app.models.Operario;
import com.unisinos.app.models.Setor;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class ImpactoAmbientalService { 
		private Setor operario = new Operario();
		private Setor engenharia = new Engenharia();
		private Setor gestaoAmbiental = new GestaoAmbiental();
		
		private Acao acaoPadrao = new Acao("Realizar plano de emergência 5523.", gestaoAmbiental);
		private ArrayList<ImpactoAmbiental> impactos = new ArrayList<>( Arrays.asList(
				new ImpactoAmbiental("Obra001", "Lixo tóxico", "Foi identificado grande quantidade de lixo tóxico próximo ao rio", "1", "123", acaoPadrao)
				));
		
		public void adicionar(ImpactoAmbiental impacto) {
			impactos.add(impacto);
			Acao acao = new Acao("Realizar plano de emergência 11002.", gestaoAmbiental);
			impacto.adicionaAcao(acao);
			acao.anexar(impacto);
		}

		public List<ImpactoAmbiental> buscarImpactos() {
			return this.impactos;
		}	
		
		public ImpactoAmbiental getImpacto(int codigo) {
			return impactos.stream().filter(a -> a.getCodigo() == codigo).findFirst().get();
		}

		private void configurarCadeia() {
			gestaoAmbiental.adicionaSetor(engenharia);
			engenharia.adicionaSetor(operario);
		}
		
		public void proximoSetor(ImpactoAmbiental impacto) {
			configurarCadeia();
			Acao acao = impacto.getAcao();
			Setor setorAtual = acao.getSetor();
			
			if(setorAtual.getResponsabilidade().equals(gestaoAmbiental.getResponsabilidade())) {
				acao.setSetor(
						setorAtual.enviarParaSetor(engenharia.getResponsabilidade())
				);
			}
			else {
				acao.setSetor(
						setorAtual.enviarParaSetor(operario.getResponsabilidade())
				);
			}			
		}
		
		public void iniciarAcao(ImpactoAmbiental impacto) {
			impacto.realizarAcao("Em andamento");
		}
		
		public void concluirAcao(ImpactoAmbiental impacto) {
			impacto.realizarAcao("Concluído");
		}
}
