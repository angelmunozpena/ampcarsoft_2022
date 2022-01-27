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

import com.ampcarsoft.entity.Coche;
import com.ampcarsoft.entity.User;
import com.ampcarsoft.repository.CocheRepository;
import com.ampcarsoft.repository.UserRepository;

@Controller
public class CochesController {

	@Autowired
	private CocheRepository dao;
	
	@Autowired
	private UserRepository daoUser;

	@RequestMapping("/wcoches")
	public ModelAndView mostrarPagina(@ModelAttribute(value = "coche") Coche coche) {
		return new ModelAndView("coches/cochesListado");
	}

	@RequestMapping("/wcochesFormulario")
	public ModelAndView mostrarPagina2(@ModelAttribute(value = "coche") Coche coche) {
		return new ModelAndView("coches/cochesFormulario");
	}

	@RequestMapping("/insertarCoche")
	public String insertar(@Valid @ModelAttribute(value = "coche") Coche coche) {
		dao.save(coche);
		return "redirect:/wcoches";
	}

	@RequestMapping("/modificarCoche")
	public String modificar(@Valid @ModelAttribute(value = "coche") Coche coche) {
		dao.save(coche);
		return "redirect:/wcoches";
	}

	@RequestMapping("/borrarCoche")
	public String borrar(@ModelAttribute(value = "coche") Coche coche,
			@RequestParam(value = "matricula") String matricula, HttpServletRequest request) {

		dao.deleteById(matricula);
		return "redirect:/wcoches";
	}

	@RequestMapping("/seleccionarCoche")
	public ModelAndView seleccionar(@ModelAttribute(value = "coche") Coche coche,
			@RequestParam(value = "matricula") String matricula, HttpServletRequest request) {

		String idCocheStr = request.getParameter("matricula");

		Optional<Coche> c = dao.findById(matricula);
		coche.setMatricula(c.get().getMatricula());
		coche.setMarca(c.get().getMarca());
		coche.setModelo(c.get().getModelo());
		coche.setPotencia(c.get().getPotencia());
		coche.setCliente(c.get().getCliente());

		return new ModelAndView("coches/cochesFormulario");
	}
	
	@RequestMapping("/verSusCoches")
	public ModelAndView seleccionarCoches(@ModelAttribute(value = "cliente") User cliente,
			@RequestParam(value = "id") Long id) {

		Optional<User> c = daoUser.findById(id);
		cliente.setUsername(c.get().getUsername());
		cliente.setCoches(c.get().getCoches());
		
		return new ModelAndView("clientes/clientesCochesListado");
	}

	@ModelAttribute(value = "listadoCoches")
	public List<Coche> getListadoCoches() {
		return (List<Coche>) dao.findAll();
	}

}
