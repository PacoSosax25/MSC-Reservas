package com.ms.vuelo.dao;

import java.util.List;

import com.ms.vuelo.model.Vuelo;

public interface VuelosDao {

	public List<Vuelo> getVuelos();
	public Vuelo getVuelo(int idVuelo);
	public void updateVuelo(Vuelo vuelo);
}
