package com.demo.currencyexchangeservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.currencyexchangeservice.bean.ExchangeRate;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeRate, Long > {

	ExchangeRate findByFromAndTo(String from,String to);
	
	ExchangeRate findByFrom(String from);
	
}
