package com.leandrosouza.user;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("Dentro de UserService... ");
		// find remote by username
		
		Optional<User> user = buildFakeUser(username);
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(
				new SimpleGrantedAuthority("ROLE_MASTER"),
				new SimpleGrantedAuthority("ROLE_ADMIN")
				);

		if (user.isPresent()) {
//			return new ResourceOwner(user.get());
			return new org.springframework.security.core.userdetails
					.User(user.get().getUsername(), user.get().getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("usuario não autorizado");
		}
	}

	private Optional<User> buildFakeUser(String username) {
		
		String encode = passwordEncoder.encode("teste");
		
		log.info("--------------------------------------------");
		log.info(encode);
		log.info("--------------------------------------------");
		
		User myUser = User.builder()
		.username(username)
		.password(encode)
		.build();
		
		return Optional.of(myUser);
	}
}
