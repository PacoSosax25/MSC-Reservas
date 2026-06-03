package com.ms.vuelo.service;

import java.util.List;

import com.ms.vuelo.model.Vuelo;

public interface VuelosService {

	List<Vuelo> getVuelosDisponibles(int plazas);
	void updatePlazas(int vuelo, int plazas);
}
