package com.cg.service;

import java.util.List;
import java.util.Optional;


import com.cg.model.User;

public interface IUserService {

	List<User> findAllUsers();
	public Optional<User> findUserById(int uid);
	public User createUser(User u);
	public String deleteUser(int uid);
	public String updateUser(int id, User u);
	public List<User> getUserByName(String name);

}
