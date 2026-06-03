package com.ms.reserva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.reserva.dao.ReservasDao;
import com.ms.reserva.model.Reserva;
@Service
public class ReservasServiceImpl implements ReservasService {
	@Autowired
	ReservasDao reservas;
	@Autowired
	RestTemplate template;
	String url = "http://SERVICIO-VUELOS";
	@Override
	public void realizarReserva(Reserva reserva, int totalPersonas) {
		reservas.generaReserva(reserva);
		template.put(url + "/vuelos/{p1}/{p2}",null,reserva.getVuelo(),totalPersonas);

	}

	@Override
	public List<Reserva> getReservas() {
		// TODO Auto-generated method stub
		return this.reservas.getReservas();
	}

}
