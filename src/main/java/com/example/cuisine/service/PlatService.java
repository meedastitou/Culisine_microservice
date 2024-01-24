package com.example.cuisine.service;

import java.util.List;

import com.example.cuisine.Model.Plat;

import jakarta.validation.Valid;

public interface PlatService {
	Plat save(@Valid Plat plat);

    List<Plat> getAll();

    Plat getOne(String id);

    void delete(Plat plat);
}
