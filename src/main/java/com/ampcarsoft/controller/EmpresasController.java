package com.ampcarsoft.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ampcarsoft.entity.Empresa;
import com.ampcarsoft.repository.EmpresaRepository;

@Controller
public class EmpresasController {

	@Autowired
	private EmpresaRepository dao;

	@RequestMapping("/mostrarEmpresas")
	public ModelAndView mostrarPagina(@ModelAttribute(value = "empresa") Empresa empresa) {
		return new ModelAndView("empresas/empresasListado");
	}

	@RequestMapping("/irAEmpresasFormulario")
	public ModelAndView mostrarPagina2(@ModelAttribute(value = "empresa") Empresa empresa) {
		return new ModelAndView("empresas/empresasFormulario");
	}

	@RequestMapping("/insertarEmpresa")
	public String insertar(@Valid @ModelAttribute(value = "empresa") Empresa empresa) {
		dao.save(empresa);
		return "redirect:/mostrarEmpresas";
	}

	@RequestMapping("/modificarEmpresa")
	public String modificar(@Valid @ModelAttribute(value = "empresa") Empresa empresa) {
		dao.save(empresa);
		return "redirect:/mostrarEmpresas";
	}

	@RequestMapping("/borrarEmpresa")
	public String borrar(@ModelAttribute(value = "empresa") Empresa empresa,
			@RequestParam(value = "cif") String cif, HttpServletRequest request) {

		dao.deleteById(cif);
		return "redirect:/mostrarEmpresas";
	}

	@RequestMapping("/seleccionarEmpresa")
	public ModelAndView seleccionar(@ModelAttribute(value = "empresa") Empresa empresa,
			@RequestParam(value = "cif") String cif, HttpServletRequest request) {

		String idEmpresaStr = request.getParameter("cif");

		Optional<Empresa> c = dao.findById(cif);
		empresa.setCif(c.get().getCif());
		empresa.setNombre(c.get().getNombre());
		empresa.setDireccion(c.get().getDireccion());
		empresa.setLogo(c.get().getLogo());

		return new ModelAndView("empresas/empresasFormulario");
	}

	@ModelAttribute(value = "listadoEmpresas")
	public List<Empresa> getListadoEmpresas() {
		return (List<Empresa>) dao.findAll();
	}

}
