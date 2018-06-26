package com.unisinos.app.controller;

import java.util.ArrayList;
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
import com.unisinos.app.service.ImpactoAmbientalService;

@Controller
public class ImpactoAmbientalController {
	@Autowired
	private ImpactoAmbientalService impactoAmbientalService;
	
	@RequestMapping(method=RequestMethod.GET, value="/cadastrarImpactoAmbiental")
	public String formulario() {
		return "impactoAmbiental/formularioImpacto";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cadastrarImpactoAmbiental")
	public String adicionar(ImpactoAmbiental impacto) {
		impactoAmbientalService.adicionar(impacto);
		return "redirect:/cadastrarImpactoAmbiental";
	}
	
	@RequestMapping("/impactos")
	public ModelAndView listaImpactos() {
		ModelAndView modelAndView = new ModelAndView("impactoAmbiental/listarImpactos");
		List<ImpactoAmbiental> impactos = impactoAmbientalService.buscarImpactos();
		modelAndView .addObject("impactos", impactos);
		return modelAndView ;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/impactoAmbiental={codigo}")
	public ModelAndView editarImpacto(@PathVariable("codigo") int codigo) {
		ImpactoAmbiental impacto = impactoAmbientalService.getImpacto(codigo);
		ModelAndView modelAndView = new ModelAndView("impactoAmbiental/editarImpactoAmbiental");
		modelAndView.addObject("impacto", impacto);
		return modelAndView;
	}	

	@RequestMapping(method=RequestMethod.GET, value="/impacto={codigo}")
	public ModelAndView proximoSetor(@PathVariable("codigo") int codigo) {
		ImpactoAmbiental impacto = impactoAmbientalService.getImpacto(codigo);
		impactoAmbientalService.proximoSetor(impacto);
		ModelAndView modelAndView = new ModelAndView("impactoAmbiental/editarImpactoAmbiental");
		modelAndView.addObject("impacto", impacto);
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/iniciarImpacto={codigo}")
	public ModelAndView iniciarAcao(@PathVariable("codigo") int codigo) {
		ImpactoAmbiental impacto = impactoAmbientalService.getImpacto(codigo);
		impactoAmbientalService.iniciarAcao(impacto);
		ModelAndView modelAndView = new ModelAndView("impactoAmbiental/editarImpactoAmbiental");
		modelAndView.addObject("impacto", impacto);
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/concluirImpacto={codigo}")
	public ModelAndView concluirAcao(@PathVariable("codigo") int codigo) {
		ImpactoAmbiental impacto = impactoAmbientalService.getImpacto(codigo);
		impactoAmbientalService.concluirAcao(impacto);
		ModelAndView modelAndView = new ModelAndView("impactoAmbiental/editarImpactoAmbiental");
		modelAndView.addObject("impacto", impacto);
		return modelAndView;
	}
}
