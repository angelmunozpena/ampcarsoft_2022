package com.ampcarsoft.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ampcarsoft.entity.Authority;
import com.ampcarsoft.entity.Coche;
import com.ampcarsoft.entity.User;
import com.ampcarsoft.repository.AuthorityRepository;
import com.ampcarsoft.repository.UserRepository;

@Controller
public class ClienteController {

	@Autowired
	private UserRepository dao;
	
	@Autowired
	private AuthorityRepository autDao;

	@RequestMapping("/cliente")
	public ModelAndView mostrarPagina(@ModelAttribute(value = "cliente") User cliente) {
		return new ModelAndView("clientes/clientesListado");
	}

	@RequestMapping("/clientesFormulario")
	public ModelAndView mostrarPagina2(@ModelAttribute(value = "cliente") User cliente) {
		return new ModelAndView("clientes/clientesFormulario");
	}

	@RequestMapping("/insertarCliente")
	public String insertar(@Valid @ModelAttribute(value = "cliente") User cliente) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		cliente.setPassword(bCryptPasswordEncoder.encode(cliente.getPassword()));
		
		Authority autCliente = autDao.findById(3L).get();
		Set<Authority> autorizaciones = new HashSet<Authority>();
		autorizaciones.add(autCliente);
		cliente.setAuthority(autorizaciones);
		dao.save(cliente);
		return "redirect:/cliente";
	}

	@RequestMapping("/modificarCliente")
	public String modificar(@Valid @ModelAttribute(value = "cliente") User cliente) {
		dao.save(cliente);
		return "redirect:/cliente";
	}

	@RequestMapping("/borrarCliente")
	public String borrar(@RequestParam(value = "id") Long id) {

		dao.deleteById(id);
		return "redirect:/cliente";
	}

	@RequestMapping("/seleccionarCliente")
	public ModelAndView seleccionar(@ModelAttribute(value = "cliente") User cliente,
			@RequestParam(value = "id") Long id, HttpServletRequest request) {

		Long idUserStr = Long.parseLong(request.getParameter("id"));

		Optional<User> c = dao.findById(id);
		cliente.setNombre(c.get().getNombre());
		cliente.setApellidos(c.get().getApellidos());
		cliente.setEdad(c.get().getEdad());
		cliente.setUsername(c.get().getUsername());
		
		return new ModelAndView("clientes/clientesFormulario");
	}

	@ModelAttribute(value = "listadoClientes")
	public List<User> getListadoUsers() {
		return (List<User>) dao.findAll();
	}
}
