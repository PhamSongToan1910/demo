package com.example.demo.service;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.model.Person;

public interface PersonService extends BaseService<PersonDTO, Long>{
	PersonDTO delete(Long ID);
	PersonDTO update(PersonDTO type);
}
