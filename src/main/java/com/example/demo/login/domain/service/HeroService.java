package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.repository.HeroDao;

@Service
public class HeroService {
	@Autowired
	HeroDao dao;
}
