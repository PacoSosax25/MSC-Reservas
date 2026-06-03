package com.ms.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.hotel.model.Hotel;
import com.ms.hotel.service.ServiceHotel;
/*anotacion para permitir acceso desde otros servidores*/
//@CrossOrigin(origins = "*")
@RestController
public class HotelesCO {
	@Autowired
	ServiceHotel service;

	@GetMapping(value = "hoteles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> devolverHoteles() {
		return service.getHotelesDisponibles();
	}
}
