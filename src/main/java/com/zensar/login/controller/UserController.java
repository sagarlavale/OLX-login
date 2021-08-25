package com.zensar.login.controller;


import com.zensar.login.dto.LoginDto;
import com.zensar.login.dto.UserDto;
import com.zensar.login.exceptions.UserAlreadyExistsException;
import com.zensar.login.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Authenticate User", notes =  "This Service Authenticates User and Returns Authentication Token in Successful Authentication")
	@ResponseBody
	public ResponseEntity<?> authenticate(@RequestBody LoginDto dto) {
		return userService.authenticate(dto);
	}
	
	@DeleteMapping(value = "/logout", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Logout User", notes =  "This Service Logs Out the User from Application")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestHeader("Authorization") String token) {
		return userService.delete(token);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Register New User", notes =  "This Service Registers a New User to Application")
	@ResponseBody
	public ResponseEntity<?> post(@RequestBody UserDto dto) throws UserAlreadyExistsException {
		return userService.post(dto);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "User Information", notes =  "This Service Returns User Information")
	@ResponseBody
	public ResponseEntity<?> get(@RequestHeader("Authorization") String token) {
		return userService.get(token);
	}

	@GetMapping(value = "/validate", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "User Information", notes =  "This Service Returns User Information")
	@ResponseBody
	public ResponseEntity<?> validate(@RequestHeader("Authorization") String token) {
		return userService.validate(token);
	}

	@GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "User Information", notes =  "This Service Returns User Information")
	@ResponseBody
	public ResponseEntity<?> info(@RequestHeader("Authorization") String token) {
		return userService.info(token);
	}

}
