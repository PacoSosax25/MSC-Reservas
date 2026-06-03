package com.ms.reserva.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ms.reserva.model.Reserva;
@Repository
public class ReservasDaoImpl implements ReservasDao {

	@Autowired
	ReservasJpaSpring reservas;
	@Override
	public void generaReserva(Reserva reserva) {
		reservas.save(reserva);

	}

	@Override
	public List<Reserva> getReservas() {
		// TODO Auto-generated method stub
		return reservas.findAll();
	}

}
