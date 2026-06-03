package com.ms.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.hotel.model.Hotel;

public interface HotelesJpaSpring extends JpaRepository<Hotel, Integer> {

}
