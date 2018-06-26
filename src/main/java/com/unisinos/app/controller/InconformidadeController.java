package com.unisinos.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unisinos.app.models.ImpactoAmbiental;
import com.unisinos.app.models.Inconformidade;
import com.unisinos.app.service.InconformidadeService;

@Controller
public class InconformidadeController {
	@Autowired
	private InconformidadeService inconformidadeService;
	
	@RequestMapping(method=RequestMethod.GET, value="/cadastrarInconformidade")
	public String formulario() {
		return "inconformidade/formularioInconformidade";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cadastrarInconformidade")
	public String adicionar(Inconformidade inconformidade) {
		inconformidadeService.adicionar(inconformidade);
		return "redirect:/cadastrarInconformidade";
	}
	
	@RequestMapping("/inconformidades")
	public ModelAndView listaInconformidades() {
		ModelAndView modelAndView = new ModelAndView("inconformidade/listarInconformidades");
		List<Inconformidade> inconformidades = inconformidadeService.buscarInconformidades();
		modelAndView .addObject("inconformidades", inconformidades);
		return modelAndView ;
	}
	
	@RequestMapping("/inconformidade={codigo}")
	public ModelAndView editarInconformidade(@PathVariable("codigo") int codigo) {
		Inconformidade inconformidade = inconformidadeService.getInconformidade(codigo);
		ModelAndView modelAndView = new ModelAndView("inconformidade/editarInconformidade");
		modelAndView.addObject("inconformidade", inconformidade);
		return modelAndView;
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/inconf={codigo}")
	public ModelAndView proximoSetor(@PathVariable("codigo") int codigo) {
		Inconformidade inconformidade = inconformidadeService.getInconformidade(codigo);
		inconformidadeService.proximoSetor(inconformidade);
		ModelAndView modelAndView = new ModelAndView("inconformidade/editarInconformidade");
		modelAndView.addObject("inconformidade", inconformidade);
		return modelAndView;
	}
	@RequestMapping(method=RequestMethod.GET, value="/iniciarInconformidade={codigo}")
	public ModelAndView iniciarAcao(@PathVariable("codigo") int codigo) {
		Inconformidade inconformidade = inconformidadeService.getInconformidade(codigo);
		inconformidadeService.iniciarAcao(inconformidade);
		ModelAndView modelAndView = new ModelAndView("inconformidade/editarInconformidade");
		modelAndView.addObject("inconformidade", inconformidade);
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/concluirInconformidade={codigo}")
	public ModelAndView concluirAcao(@PathVariable("codigo") int codigo) {
		Inconformidade inconformidade = inconformidadeService.getInconformidade(codigo);
		inconformidadeService.concluirAcao(inconformidade);
		ModelAndView modelAndView = new ModelAndView("inconformidade/editarInconformidade");
		modelAndView.addObject("inconformidade", inconformidade);
		return modelAndView;
	}
}
