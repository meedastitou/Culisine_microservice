package com.example.cuisine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cuisine.Model.Plat;


public interface PlatRepository extends MongoRepository<Plat,String> {

}
