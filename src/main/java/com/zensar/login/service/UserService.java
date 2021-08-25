package com.zensar.login.service;

import com.zensar.login.dto.LoginDto;
import com.zensar.login.dto.UserDto;
import com.zensar.login.exceptions.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;

public interface UserService {
	
	ResponseEntity<?> authenticate(LoginDto loginDto);
	
	ResponseEntity<?> delete(String token);
	
	ResponseEntity<?> post(UserDto userDto) throws UserAlreadyExistsException;
	
	ResponseEntity<?> get(String token);

	ResponseEntity<?> validate(String token);

	ResponseEntity<?> info(String token);

}
