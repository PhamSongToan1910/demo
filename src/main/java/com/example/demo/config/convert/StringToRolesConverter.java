package com.example.demo.config.convert;

import java.util.Collection;
import java.util.Optional;

import javax.management.relation.RoleInfoNotFoundException;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.model.Roles;
import com.example.demo.repository.RoleRepository;

public class StringToRolesConverter extends AbstractConverter<String, Roles>{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	protected Roles convert(String source) {
		// TODO Auto-generated method stub
		Optional<Roles> role = this.roleRepository.findByName(source);
		if(role.isEmpty()) {
			throw new RoleNotFoundException("Role is not exists");
		}else {
			System.out.println(role.get().getID());
			return role.get();
		}
	}

}
