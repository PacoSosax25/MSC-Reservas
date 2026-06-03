package com.ms.reserva.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.reserva.model.Reserva;

public interface ReservasJpaSpring extends JpaRepository<Reserva, Integer> {

}
