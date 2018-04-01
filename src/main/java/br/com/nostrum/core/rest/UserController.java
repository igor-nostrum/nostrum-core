package br.com.nostrum.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.nostrum.core.model.*;

@RestController
@RequestMapping(path="/public/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestBody User user) {
		userRepository.save(user);
		return "Saved";
	}

	@RequestMapping(path="/list")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
}