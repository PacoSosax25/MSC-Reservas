package com.ms.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.hotel.dao.HotelesDao;
import com.ms.hotel.model.Hotel;
@Service
public class ServiceHotelesImpl implements ServiceHotel {
	@Autowired
	HotelesDao dao;
	@Override
	public List<Hotel> getHotelesDisponibles() {
		List<Hotel> hoteles = dao.getHoteles();
		return hoteles.stream().filter(t->t.getDisponible()==1).collect(Collectors.toList());
		 
	}

}
