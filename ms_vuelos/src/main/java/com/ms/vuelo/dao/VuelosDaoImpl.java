package com.ms.vuelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ms.vuelo.model.Vuelo;

import jakarta.transaction.Transactional;

@Repository
public class VuelosDaoImpl implements VuelosDao {
	@Autowired
	VuelosJpaSpring vuelosSpring;
	@Override
	public List<Vuelo> getVuelos() {
		// TODO Auto-generated method stub
		return vuelosSpring.findAll();
	}

	@Override
	public Vuelo getVuelo(int idVuelo) {
		// TODO Auto-generated method stub
		return vuelosSpring.findById(idVuelo).orElse(null);
	}
	@Transactional
	@Override
	public void updateVuelo(Vuelo vuelo) {
		// TODO Auto-generated method stub
		vuelosSpring.save(vuelo);
	}

}
