package com.ms.reserva.service;

import java.util.List;

import com.ms.reserva.model.Reserva;

public interface ReservasService {
	
	void realizarReserva(Reserva reserva, int totalPersonas);
	
	List<Reserva> getReservas();

}
