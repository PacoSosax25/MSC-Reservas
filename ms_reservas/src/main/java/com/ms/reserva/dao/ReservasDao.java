package com.ms.reserva.dao;

import java.util.List;

import com.ms.reserva.model.Reserva;

public interface ReservasDao {
	public void generaReserva(Reserva reserva);
	
	List<Reserva> getReservas();

}
