package com.ms.vuelo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vuelo.model.Vuelo;
import com.ms.vuelo.service.VuelosService;

//@CrossOrigin(origins = "*")
@RestController
public class VuelosCO {
	@Autowired
	VuelosService service;

	@GetMapping(value = "vuelos/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> getVuelos(@PathVariable("plazas") int plazas) {
		List<Vuelo> vuelos = service.getVuelosDisponibles(plazas);
		return vuelos;
	}

	@PutMapping(value = "vuelos/{idvuelo}/{plazas}")
	public void updateVuelo(@PathVariable("idvuelo") int id, @PathVariable("plazas") int plazas) {
		service.updatePlazas(id, plazas);
	}
}
