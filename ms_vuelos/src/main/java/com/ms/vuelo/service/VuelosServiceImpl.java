package com.ms.vuelo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vuelo.dao.VuelosDao;
import com.ms.vuelo.model.Vuelo;
@Service
public class VuelosServiceImpl implements VuelosService {
	@Autowired
	VuelosDao dao;
	@Override
	public List<Vuelo> getVuelosDisponibles(int plazas) {
		// TODO Auto-generated method stub
		return dao.getVuelos().stream().filter(t->t.getPlazas()>=plazas).collect(Collectors.toList());
	}

	@Override
	public void updatePlazas(int id, int plazas) {
		Vuelo vuelo = dao.getVuelo(id);
		if(vuelo!=null) {
		vuelo.setPlazas(vuelo.getPlazas()-plazas);
		dao.updateVuelo(vuelo);
		}

	}

}
