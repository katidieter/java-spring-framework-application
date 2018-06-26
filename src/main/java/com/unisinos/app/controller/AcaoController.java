package com.unisinos.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unisinos.app.models.Acao;
import com.unisinos.app.models.ImpactoAmbiental;
import com.unisinos.app.service.AcaoService;


@Controller
public class AcaoController {
	
	@Autowired
	private AcaoService acaoService;
	
	@RequestMapping(method=RequestMethod.GET, value="/cadastrarAcao")
	public String formulario() {
		return "acao/formularioAcao";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cadastrarAcao")
	public String adicionar(Acao acao) {
		acaoService.adicionar(acao);
		return "redirect:/cadastrarAcao";
	}
	
	@RequestMapping("/acoes")
	public ModelAndView listaAcoes() {
		ModelAndView modelAndView = new ModelAndView("acao/listarAcoes");
		List<Acao> acoes = acaoService.buscarAcoes();
		modelAndView .addObject("acoes", acoes);
		return modelAndView ;
	}
	
	@RequestMapping("/acao={codigo}")
	public ModelAndView editarAcao(@PathVariable("codigo") int codigo) {
		Acao acao = acaoService.getAcao(codigo);
		ModelAndView modelAndView = new ModelAndView("acao/editarAcao");
		modelAndView.addObject("acao", acao);
		return modelAndView;
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/iniciarAcao={codigo}")
	public ModelAndView iniciarAcao(@PathVariable("codigo") int codigo) {
		Acao acao = acaoService.getAcao(codigo);
		acaoService.iniciarAcao(acao);
		ModelAndView modelAndView = new ModelAndView("acao/editarAcao");
		modelAndView.addObject("acao", acao);
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/concluirAcao={codigo}")
	public ModelAndView concluirAcao(@PathVariable("codigo") int codigo) {
		Acao acao = acaoService.getAcao(codigo);
		acaoService.concluirAcao(acao);
		ModelAndView modelAndView = new ModelAndView("acao/editarAcao");
		modelAndView.addObject("acao", acao);
		return modelAndView;
	}
}
