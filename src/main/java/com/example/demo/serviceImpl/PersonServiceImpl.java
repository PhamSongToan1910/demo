package com.example.demo.serviceImpl;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PersonDTO getById(Long key) {
		Optional<Person> existsPerson = this.personRepository.findById(key);
		if(existsPerson.get() != null) {
			return modelMapper.map(existsPerson, PersonDTO.class);
		}else {
			throw new AccountNotFoundException("Account not found");
		}
	}

	@Override
	public List<PersonDTO> getAll() {
		// TODO Auto-generated method stub
		List<Person> listPersons = this.personRepository.findAll();
		if (!listPersons.isEmpty()) {
            Type listType = new TypeToken<List<PersonDTO>>() {}.getType();
            return modelMapper.map(listPersons, listType);
        } else {
            return Collections.emptyList();
        }
	}

	@Override
	public PersonDTO create(PersonDTO type) {
		// TODO Auto-generated method stub
		Person person = modelMapper.map(type, Person.class);
		Person checkPerson = this.personRepository.findByUsername(person.getUsername());
		if(checkPerson != null) {
			throw new AccountNotFoundException("Username is existed");
		}
		this.personRepository.save(person);
		return type;
	}

	@Override
	public PersonDTO update(PersonDTO type) {
		// TODO Auto-generated method stub
		Optional<Person> person = this.personRepository.findById(type.getID());
		if(person.get()!=null) {
			Date date = new Date();
			person.get().setName(type.getName());
			person.get().setUpdateDate(date);
			this.personRepository.save(person.get());
			return type;
		}else {
			throw new AccountNotFoundException("Person not found");
		}
	}

	@Override
	public PersonDTO delete(Long ID) {
		// TODO Auto-generated method stub
		Optional<Person> person = this.personRepository.findById(ID);
		if(person.get()!=null) {
			person.get().setStatus(false);
			this.personRepository.save(person.get());
			return modelMapper.map(person, PersonDTO.class);
		}else {
			throw new AccountNotFoundException("Person not found");
		}
	}

}
