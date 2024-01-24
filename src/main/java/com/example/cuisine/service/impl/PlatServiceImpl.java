package com.example.cuisine.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cuisine.Model.Plat;
import com.example.cuisine.repository.PlatRepository;
import com.example.cuisine.service.PlatService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlatServiceImpl implements PlatService{
	
	private final PlatRepository platRepository;
	
	@Override
	public Plat save(@Valid Plat plat) {
		 return this.platRepository.save(plat);
	}

	@Override
	public List<Plat> getAll() {
		
		return this.platRepository.findAll();
	}

	@Override
	public Plat getOne(String id) {
		
		return this.platRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Error: Plat is not found."));
	}

	@Override
	public void delete(Plat plat) {
		this.platRepository.delete(null);;
	}

}
