package com.ms.hotel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ms.hotel.model.Hotel;
@Repository
public class HotelesDaoImpl implements HotelesDao {
	@Autowired
	HotelesJpaSpring hoteles;

	@Override
	public List<Hotel> getHoteles() {
		// TODO Auto-generated method stub
		return hoteles.findAll();
	}

}
