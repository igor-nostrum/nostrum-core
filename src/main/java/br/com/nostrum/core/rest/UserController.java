package br.com.nostrum.core.rest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.nostrum.core.model.*;

@RestController
@RequestMapping(path="/public/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestBody User user) {
		String passwordPlain = RandomStringUtils.randomAlphanumeric(10);
		String passwordCrypted = passwordEncoder.encode(passwordPlain);
		user.setPassword(passwordCrypted);
		userRepository.save(user);
		return "Saved";
	}

	@RequestMapping(path="/list")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
}