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


@Service
public class AcaoService {
	private Setor operario = new Operario();
	private Setor engenharia = new Engenharia();
	private Setor gestaoAmbiental = new GestaoAmbiental();
	
	private ArrayList<Acao> acoes = new ArrayList<>( Arrays.asList(
			new Acao("Realizar treinamento com a equipe", gestaoAmbiental),
			new Acao("Salvar o mundo", engenharia ),
			new Acao("Remover lixo tóximo acumulado", operario)
			));

	public void adicionar(Acao acao) {
		acoes.add(acao);
	}

	public ArrayList<Acao> buscarAcoes() {
		return this.acoes;
	}	
	
	public Acao getAcao(int codigo) {
		return acoes.stream().filter(a -> a.getCodigo() == codigo).findFirst().get();
	}
	
	public void iniciarAcao(Acao acao) {
		acao.setSitacao("Em andamento");
		acao.enviarNotificacao();
	}
	
	public void concluirAcao(Acao acao) {
		acao.setSitacao("Concluído");
		acao.enviarNotificacao();
	}
}

