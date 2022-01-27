package com.ampcarsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampcarsoft.service.InformeService;

@RestController
public class InformesController {

	@Autowired
	private InformeService service;

	@GetMapping("/winformes")
	public ResponseEntity<byte[]> mostrarPagina2() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(service.generarInforme(), headers, HttpStatus.OK);
		return response;
	}
}
