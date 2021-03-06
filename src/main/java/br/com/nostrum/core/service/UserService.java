package br.com.nostrum.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.GrantedAuthority;

import br.com.nostrum.core.model.*;

@Service
public class UserService implements UserDetailsService {

	private UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByName(username);

		if (user == null) {
			throw new UsernameNotFoundException("Usuário ou senha inválidos.");
		}
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		if (username.equals("admin")) {
			auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		}

		String password = user.getPassword();
		return new org.springframework.security.core.userdetails.User(username, password, auth);
	}
}