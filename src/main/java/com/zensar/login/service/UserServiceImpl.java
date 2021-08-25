package com.zensar.login.service;

import com.zensar.login.config.JwtUtils;
import com.zensar.login.dto.JwtResponse;
import com.zensar.login.dto.LoginDto;
import com.zensar.login.dto.UserDto;
import com.zensar.login.entity.User;
import com.zensar.login.exceptions.EmptyTokenException;
import com.zensar.login.exceptions.InvalidCredentialsException;
import com.zensar.login.exceptions.InvalidTokenException;
import com.zensar.login.exceptions.UserAlreadyExistsException;
import com.zensar.login.mapper.UserMapper;
import com.zensar.login.repository.UserDetailsImpl;
import com.zensar.login.repository.UserRepository;
import com.zensar.login.util.ErrorResponse;
import com.zensar.login.util.Token;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
    JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public ResponseEntity<?> authenticate(LoginDto loginDto) {
		try
        {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
            if (authentication!=null)
            {
				UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateToken(userDetails.getUsername());

                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getPhone(),
                        userDetails.getEmail()));
            }
            else
            {
				throw new InvalidCredentialsException();
            }
        }
        catch (Exception e)
        {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(Integer.valueOf(Token.INVALID_USER.getValue()));
            errorResponse.setStatus(Token.INVALID_USER.name());
            errorResponse.setMessage("Invalid Username or Password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
	}

	@Override
	public ResponseEntity<?> delete(String token) {
		return ResponseEntity.ok(true) ;
	}

	@Override
	public ResponseEntity<?> post(UserDto dto) throws UserAlreadyExistsException {

		if (userRepository.existsByUserName(dto.getUserName()))
			throw new UserAlreadyExistsException("UserName Already Exists");
		if (userRepository.existsByPhone(dto.getPhone()))
			throw new UserAlreadyExistsException("Phone Already Exists");
		if (userRepository.existsByEmail(dto.getEmail()))
			throw new UserAlreadyExistsException("Email Already Exists");

		dto.setPassword(encoder.encode(dto.getPassword()));
		return ResponseEntity.ok(userMapper.toUserDto(userRepository.save(userMapper.toUser(dto)))) ;
	}

	@Override
	public ResponseEntity<?> get(String token) {
		return getResponseEntity(token);

	}

	@Override
	public ResponseEntity<?> validate(String token) {
		if(null==token || "".equalsIgnoreCase(token))
		{
			throw new EmptyTokenException(token);
		}
		try {
			Claims claims = jwtUtils.decodeJWT(token);
		}
		catch (Exception e) {
			throw new InvalidTokenException(token);
		}
		String userName = jwtUtils.getUserNameFromJwtToken(token);

		UserDetailsImpl userDetails = (UserDetailsImpl) loadUserByUsername(userName);

		return ResponseEntity.ok(jwtUtils.validateToken(token,userDetails));

	}

	@Override
	public ResponseEntity<?> info(String token) {
		return getResponseEntity(token);
	}

	private ResponseEntity<?> getResponseEntity(String token) {

		if(jwtUtils.isTokenEmpty(token))
		{
			throw new EmptyTokenException(token);
		}

		try {
			Claims claims = jwtUtils.decodeJWT(token);
		}
		catch (Exception e) {
			throw new InvalidTokenException(token);
		}

		String userName = jwtUtils.getUserNameFromJwtToken(token);

		UserDetailsImpl userDetails = (UserDetailsImpl) loadUserByUsername(userName);

		if (jwtUtils.validateToken(token,userDetails))
		{
			return ResponseEntity.ok(new JwtResponse(
					userDetails.getId(),
					userDetails.getFirstName(),
					userDetails.getLastName(),
					userDetails.getPhone(),
					userDetails.getEmail()));
		}

		else
			throw new InvalidTokenException(token);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByUserName(username);
	        if (user == null)
	        {
	            throw new UsernameNotFoundException("User Not Found with username: " + username);
	        }
	        else
	            return UserDetailsImpl.build(user);
	}
}
