package com.buchi.assessment.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.buchi.assessment.exception.InvalidCredentialsException;
import com.buchi.assessment.model.User;
import com.buchi.assessment.repository.RolesRepository;
import com.buchi.assessment.repository.UserReository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserReository userRepository;

	@Autowired
	RolesRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		if (StringUtils.isBlank(username)) {
			throw new BadCredentialsException("Bad credentials");
		}

		Optional<User> user = userRepository.findByEmail(username);
		if(!user.isPresent()) {
			throw new InvalidCredentialsException("No user exists with username :".concat(username));
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		List<String> userRoles = roleRepository.getUserRoles(user.get().getId());
		if(authorities != null && !authorities.isEmpty()) {
			for(String role : userRoles) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_".concat(role));
				authorities.add(authority);
			}

		}

		return (UserDetails) new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);

	}

}
