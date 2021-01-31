package com.cartorioapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cartorioapp.models.Cartorio;
import com.cartorioapp.models.Certidao;
import com.cartorioapp.repository.CartorioRepository;
import com.cartorioapp.repository.CertidaoRepository;
import javax.validation.Valid;


@Controller
public class CartorioController {
	
	@Autowired
	private CartorioRepository cr;
	@Autowired
	private CertidaoRepository cd;
	
	@RequestMapping(value="/cadastrarCartorio", method=RequestMethod.GET)
	public String form() {
		return "cartorio/formCartorio";	
	}
	
	
	//Cadastra cartorio
	@RequestMapping(value="/cadastrarCartorio", method=RequestMethod.POST)
	public String form(@Valid Cartorio cartorio, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarCartorio";
		}
		
		cr.save(cartorio);
		attributes.addFlashAttribute("mensagem", "Cartorio cadastrado com sucesso!");
		return "redirect:/cadastrarCartorio";
	}
	
	
	@RequestMapping("/cartorio")
	public ModelAndView listaCartorios() {
		ModelAndView mv= new ModelAndView("listaCartorios");
		Iterable<Cartorio> cartorios= cr.findAll();
		mv.addObject("cartorios", cartorios);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesCartorio(@PathVariable("codigo") long codigo){
		Cartorio cartorio = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("cartorio/detalhesCartorio");
		mv.addObject("cartorio", cartorio);
		
		 Iterable<Certidao> certidoes = cd.findByCartorio(cartorio);
		 mv.addObject("certidao", certidoes);
		
		return mv;
	}
	
	//deleta Cartorio
	@RequestMapping("/deletarCartorio")
	public String deletarCartorio(long codigo){
	    Cartorio cartorio = cr.findByCodigo(codigo);
		cr.delete(cartorio);
		return "redirect:/cartorio";
	}
	
	
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesCartorioPost(@PathVariable("codigo") long codigo, @Valid Certidao certidao,  BindingResult result, RedirectAttributes attributes) {
		
	
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Cartorio cartorio = cr.findByCodigo(codigo);
		certidao.setCartorio(cartorio);
		cd.save(certidao);
		attributes.addFlashAttribute("mensagem", "Certidao adicionada com sucesso!");
		return "redirect:/{codigo}";
		
	 }
	

	
	  //deleta Certidao
	  @RequestMapping("/deletarCertidao")
	  public String deletarCertidao(String nomeCertidao){
	  Certidao certidao = cd.findByNomeCertidao(nomeCertidao);
		cd.delete(certidao);
		
		Cartorio cartorio = certidao.getCartorio();
		long codigoLong = cartorio.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
	
	
	  @RequestMapping(value = "/editarCartorio", method = RequestMethod.GET)
	    public ModelAndView editarCartorio(long codigo) {
	        Cartorio cartorio = cr.findByCodigo(codigo);
	        ModelAndView mv = new ModelAndView("cartorio/editarCartorio");
	        mv.addObject("cartorio", cartorio);
	        return mv;
	    }

	    // Updating evento
	    @RequestMapping(value = "/editarCartorio", method = RequestMethod.POST)
	    public String updateCartorio(@Valid Cartorio cartorio, BindingResult result, RedirectAttributes attributes) {
	        cr.save(cartorio);
	        attributes.addFlashAttribute("successo", "Evento alterado com sucesso!");
	        //return "redirect:/";
	     
	        return "redirect:/cartorio";
	    }
	
}
