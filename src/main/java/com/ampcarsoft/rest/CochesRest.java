package com.ampcarsoft.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampcarsoft.entity.Coche;
import com.ampcarsoft.repository.CocheRepository;

@RestController("/rest")
public class CochesRest {

	@Autowired
	private CocheRepository dao;

	@PostMapping("/create")
	public Coche insertar(@RequestBody Coche coche) {
		return dao.save(coche);
	}

	@PutMapping("/update")
	public Coche modificar(@RequestBody Coche coche) {
		return dao.save(coche);
	}

	@DeleteMapping("/delete/{matricula}")
	public void borrar(@PathVariable String matricula) {
		dao.deleteById(matricula);
	}

	@GetMapping("/get/{matricula}")
	public Coche seleccionar(@PathVariable String matricula) {
		Optional<Coche> c = dao.findById(matricula);
		return c.get();
	}

	@GetMapping("/all")
	public List<Coche> getListadoCoches() {
		return (List<Coche>) dao.findAll();
	}

}
