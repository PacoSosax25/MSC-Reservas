package com.ms.vuelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.vuelo.model.Vuelo;

public interface VuelosJpaSpring extends JpaRepository<Vuelo, Integer> {

}
