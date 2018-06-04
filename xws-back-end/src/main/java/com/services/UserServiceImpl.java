package com.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import com.model.User;
import com.model.dto.RegistrationUserDto;
import com.model.dto.LoginUserDto;
import com.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean registerUser(RegistrationUserDto rUDTO) {
		User u = new User();
		if (rUDTO == null) {
			System.out.println("User je null!");
			return false;
		}
		if (!(rUDTO.getPassword().equals(rUDTO.getPasswordConfirmation()))) {
			System.out.println("Lozinke se ne poklapaju!");
			return false;
		}
		u.setUsername(rUDTO.getUsername());
		u.setIme(rUDTO.getFirstname());
		u.setPrezime(rUDTO.getLastname());
		u.setPassword(rUDTO.getPassword());

		try {

			userRepository.save(u);
			return true;

		} catch (Exception e) {

			System.out.println("Greska prilikom upisa u bazu!");
			return false;
		}

	}

	public User findUser(LoginUserDto loginUserDTO) {
		System.out.println(
				userRepository.findByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword()));
		return userRepository.findByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword());
	}

	public void setCurrentUser(User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("loggedUser"));
		Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getHjid(), null);
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("Ulogovan je:" + user);
		System.out.println(SecurityContextHolder.getContext());
	}
	
	

	public User getCurrentUser() {
		System.out.println("getcurrentuser****");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(SecurityContextHolder.getContext());
		try {
			Long id = Long.parseLong(auth.getName());
			System.out.println("id" + id);
			return userRepository.findOneByHjid(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	public User getLoggedUserById(Long hjid) {
		try {
			return userRepository.findOneByHjid(hjid);
		} catch (Exception e) {
			return null;
		}
	}

}
